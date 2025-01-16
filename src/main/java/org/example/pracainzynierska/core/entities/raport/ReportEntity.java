package org.example.pracainzynierska.core.entities.raport;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReportEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus reportStatus;

    @ManyToMany
    @JoinTable(
            name = "report_steam_games",
            joinColumns = @JoinColumn(name = "raport_id"),
            inverseJoinColumns = @JoinColumn(name = "steam_game_id")
    )
    private Set<SteamGameEntity> steamGames = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "report_user_game_rating",
            joinColumns = @JoinColumn(name = "raport_id"),
            inverseJoinColumns = @JoinColumn(name = "user_game_rating_id")
    )
    private Set<GameRatingEntity> gameRatings = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "report_user",
            joinColumns = @JoinColumn(name = "raport_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserEntity> users = new HashSet<>();

}
