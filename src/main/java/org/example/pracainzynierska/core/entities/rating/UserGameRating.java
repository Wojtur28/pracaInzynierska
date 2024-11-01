package org.example.pracainzynierska.core.entities.rating;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.game.GameEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_game_ratings", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "game_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGameRating extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rating rating;
}
