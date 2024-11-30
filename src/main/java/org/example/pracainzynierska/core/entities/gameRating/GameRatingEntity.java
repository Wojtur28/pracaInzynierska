package org.example.pracainzynierska.core.entities.gameRating;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "game_ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "steam_games_id")
    private SteamGameEntity steamGames ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Integer rating;

    private String content;

    private int votesUp;

    private int votesDown;
}
