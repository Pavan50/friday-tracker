package com.bytesedge.fridaytracker.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PresentationDto {
    private Long id;
    private String title;
    private String description;
    private Long presenterId;
    private String presenterName;
    private LocalDateTime createdAt;
    private int upvotes;
    private int downvotes;
    private Boolean userVote;  // null if not voted, true for upvote, false for downvote
}
