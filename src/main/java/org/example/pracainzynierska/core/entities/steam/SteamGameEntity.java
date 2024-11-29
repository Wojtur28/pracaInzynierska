package org.example.pracainzynierska.core.entities.steam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "steam_games")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "steam_games", indexes = {
        @Index(name = "idx_app_id", columnList = "app_id")
})
public class SteamGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "app_id", unique = true, nullable = false)
    private Long appId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "reviews_total", nullable = false)
    private Integer reviewsTotal;

    @Column(name = "reviews_score_fancy", nullable = false)
    private String reviewsScoreFancy;

    @Column(name = "release_date", nullable = false)
    @EqualsAndHashCode.Exclude
    private LocalDate releaseDate;

    @Column(name = "launch_price", nullable = false)
    private String launchPrice;

    @Column(name = "tags", nullable = false, columnDefinition = "TEXT")
    private String tags;

    @Column(name = "steam_page", nullable = false)
    private String steamPage;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SteamReviewEntity> steamReviews = new HashSet<>();
}

