package org.example.pracainzynierska.core.entities.gameView;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_views")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class GameViewEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "steam_game_id", referencedColumnName = "id", nullable = false)
    private SteamGameEntity steamGame;

    @Column(name = "view_duration", nullable = false)
    private int viewDuration;

    @Column(name = "viewed_at", nullable = false)
    private LocalDateTime viewedAt;

}

