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

@EqualsAndHashCode(callSuper = true)
@Entity(name = "game_ratings_answers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRatingAnswerEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "game_rating_entity_id")
    private GameRatingEntity gameRatingEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String content;

    private int votesUp;

    private int votesDown;

}
