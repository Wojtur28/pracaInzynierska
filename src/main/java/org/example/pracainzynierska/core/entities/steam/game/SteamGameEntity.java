package org.example.pracainzynierska.core.entities.steam.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "steam_games")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SteamGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
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
    private LocalDate releaseDate;

    @Column(name = "launch_price", nullable = false)
    private String launchPrice;

    @Column(name = "tags", nullable = false, columnDefinition = "TEXT")
    private String tags;

    @Column(name = "steam_page", nullable = false)
    private String steamPage;

    @OneToOne(mappedBy = "steamGameEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private SteamGameDetailEntity steamGameDetailEntity;
}
