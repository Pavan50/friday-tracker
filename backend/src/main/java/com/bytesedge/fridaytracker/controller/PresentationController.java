package com.bytesedge.fridaytracker.controller;

import com.bytesedge.fridaytracker.dto.PresentationDto;
import com.bytesedge.fridaytracker.service.PresentationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/presentations")
@RequiredArgsConstructor
public class PresentationController {
    private final PresentationService presentationService;

    @GetMapping
    public ResponseEntity<List<PresentationDto>> getAllPresentations(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(presentationService.getAllPresentations(userDetails.getUsername()));
    }

    @PostMapping
    public ResponseEntity<PresentationDto> createPresentation(
            @RequestBody Map<String, String> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(presentationService.createPresentation(
            request.get("title"),
            request.get("description"),
            userDetails.getUsername()
        ));
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<PresentationDto> vote(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(presentationService.vote(
            id,
            request.get("isUpvote"),
            userDetails.getUsername()
        ));
    }
}
