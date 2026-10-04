package com.bytesedge.fridaytracker.service;

import com.bytesedge.fridaytracker.dto.PresentationDto;
import com.bytesedge.fridaytracker.entity.Presentation;
import com.bytesedge.fridaytracker.entity.User;
import com.bytesedge.fridaytracker.entity.Vote;
import com.bytesedge.fridaytracker.repository.PresentationRepository;
import com.bytesedge.fridaytracker.repository.UserRepository;
import com.bytesedge.fridaytracker.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PresentationService {
    private final PresentationRepository presentationRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    @Transactional(readOnly = true)
    public List<PresentationDto> getAllPresentations(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return presentationRepository.findAllWithPresenterAndVotes().stream()
                .map(presentation -> convertToDto(presentation, user))
                .collect(Collectors.toList());
    }

    @Transactional
    public PresentationDto createPresentation(String title, String description, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Presentation presentation = new Presentation();
        presentation.setTitle(title);
        presentation.setDescription(description);
        presentation.setPresenter(user);
        presentation.setCreatedAt(LocalDateTime.now());
        presentation.setUpvotes(0);
        presentation.setDownvotes(0);

        presentation = presentationRepository.save(presentation);
        return convertToDto(presentation, user);
    }

    @Transactional
    public PresentationDto vote(String presentationId, boolean isUpvote, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Presentation presentation = presentationRepository.findById(presentationId)
                .orElseThrow(() -> new RuntimeException("Presentation not found"));

        Vote vote = voteRepository.findByUserAndPresentation(user, presentation)
                .orElseGet(() -> {
                    Vote newVote = new Vote();
                    newVote.setUser(user);
                    newVote.setPresentation(presentation);
                    return newVote;
                });

        vote.setUpvote(isUpvote);
        voteRepository.save(vote);
        updateVoteCount(presentation);

        presentation = presentationRepository.save(presentation);
        return convertToDto(presentation, user);
    }

    private void updateVoteCount(Presentation presentation) {
        presentation.setUpvotes((int) presentation.getVotes().stream().filter(Vote::isUpvote).count());
        presentation.setDownvotes((int) presentation.getVotes().stream().filter(vote -> !vote.isUpvote()).count());
    }

    private PresentationDto convertToDto(Presentation presentation, User currentUser) {
        Vote userVote = presentation.getVotes().stream()
                .filter(vote -> vote.getUser().getId().equals(currentUser.getId()))
                .findFirst()
                .orElse(null);

        return PresentationDto.builder()
                .id(presentation.getId())
                .title(presentation.getTitle())
                .description(presentation.getDescription())
                .presenterId(presentation.getPresenter().getId())
                .presenterName(presentation.getPresenter().getName())
                .createdAt(presentation.getCreatedAt())
                .upvotes(presentation.getUpvotes())
                .downvotes(presentation.getDownvotes())
                .userVote(userVote != null ? userVote.isUpvote() : null)
                .build();
    }
}
