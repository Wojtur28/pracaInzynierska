package org.example.pracainzynierska.core.entities.gameRating;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "game_ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingEntity extends BaseEntity {

    private Integer rating;

    private String content;

    @ManyToOne
    @JoinColumn(name = "steam_games_id")
    private SteamGameEntity steamGame;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "gameRatingEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GameRatingAnswerEntity> answers = new HashSet<>();
}

