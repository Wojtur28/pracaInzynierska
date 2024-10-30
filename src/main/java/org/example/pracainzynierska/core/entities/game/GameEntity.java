package org.example.pracainzynierska.core.entities.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.genre.GenreEntity;
import org.example.pracainzynierska.core.entities.rating.UserGameRating;
import org.example.pracainzynierska.core.entities.theme.ThemeEntity;

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

    @Column(unique = true, nullable = false)
    private Long apiId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_themes",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private Set<ThemeEntity> themes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "game_platforms", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "platform_id")
    private Set<Platform> platforms = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "game_screenshots", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "screenshot_id")
    private Set<Integer> screenshots = new HashSet<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserGameRating> userRatings = new HashSet<>();
}
