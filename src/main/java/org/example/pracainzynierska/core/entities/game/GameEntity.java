package org.example.pracainzynierska.core.entities.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity extends BaseEntity {

    private String title;

    @Enumerated(EnumType.STRING)
    private GameCategory category;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "genre", nullable = false)
    private Set<String> genres = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "game_themes", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "theme", nullable = false)
    private Set<String> themes = new HashSet<>();

    @ElementCollection(targetClass = Platform.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "game_platform", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "platform")
    @Enumerated(EnumType.STRING)
    private Set<Platform> platforms;

    @ManyToMany(mappedBy = "playedGames")
    private Set<UserEntity> usersPlayed = new HashSet<>();

    private boolean isSteamDeckSupport;
}
