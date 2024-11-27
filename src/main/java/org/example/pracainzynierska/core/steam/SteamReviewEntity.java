package org.example.pracainzynierska.core.steam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "steam_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "steam_reviews")
public class SteamReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "review_id", unique = true, nullable = false)
    private String reviewId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_id", nullable = false)
    private SteamGameEntity game;

    @Column(name = "user_steam_id", nullable = false)
    private String userSteamId;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "timestamp_created", nullable = false)
    private LocalDateTime timestampCreated;

    @Column(name = "votes_up", nullable = false)
    private Integer votesUp;

    @Column(name = "votes_down", nullable = false)
    private Integer votesDown;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "steam_user_id")
    private SteamUserEntity steamUser;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
