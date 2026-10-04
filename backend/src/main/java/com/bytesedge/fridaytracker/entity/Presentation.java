package com.bytesedge.fridaytracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "presentations")
@Data
@NoArgsConstructor
public class Presentation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presenter_id", nullable = false)
    private User presenter;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private int upvotes = 0;
    private int downvotes = 0;

    @OneToMany(mappedBy = "presentation", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();
}
