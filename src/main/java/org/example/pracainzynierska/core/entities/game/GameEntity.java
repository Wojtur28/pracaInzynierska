package org.example.pracainzynierska.core.entities.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.genre.GenreEntity;
import org.example.pracainzynierska.core.entities.platform.PlatformEntity;
import org.example.pracainzynierska.core.entities.rating.UserGameRating;
import org.example.pracainzynierska.core.entities.screenshot.ScreenshotEntity;
import org.example.pracainzynierska.core.entities.theme.ThemeEntity;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class GameEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<PlatformEntity> platforms = new HashSet<>();

    //@JsonManagedReference
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScreenshotEntity> screenshots = new HashSet<>();

    @Column(name = "api_rating")
    private double apiRating;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserGameRating> userRatings = new HashSet<>();

    @Override
    public String toString() {
        return "GameEntity{" +
                "name='" + name + '\'' +
                ", apiId=" + apiId +
                ", genres=" + genres +
                ", themes=" + themes +
                ", platforms=" + platforms +
                ", screenshots=" + screenshots +
                ", apiRating=" + apiRating +
                ", userRatings=" + userRatings +
                '}';
    }
}
