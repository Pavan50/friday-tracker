package com.bytesedge.fridaytracker.repository;

import com.bytesedge.fridaytracker.entity.Presentation;
import com.bytesedge.fridaytracker.entity.User;
import com.bytesedge.fridaytracker.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, String> {
    Optional<Vote> findByUserAndPresentation(User user, Presentation presentation);
}
