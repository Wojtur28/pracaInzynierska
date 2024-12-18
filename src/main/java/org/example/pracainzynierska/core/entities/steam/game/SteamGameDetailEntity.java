package org.example.pracainzynierska.core.entities.steam.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.category.CategoryEntity;
import org.example.pracainzynierska.core.entities.steam.genre.GenreEntity;
import org.example.pracainzynierska.core.entities.steam.platform.PlatformEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "steam_game_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SteamGameDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "steam_game_entity_id")
    @EqualsAndHashCode.Exclude
    private SteamGameEntity steamGameEntity;

    @Column(columnDefinition = "TEXT")
    private String headerImage;

    @Column(columnDefinition = "TEXT")
    private String capsuleImage;

    @Column(columnDefinition = "TEXT")
    private String website;

    private String developer;

    private String publisher;

    private int requiredAge;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String supportedLanguages;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "steam_game_details_categories",
            joinColumns = @JoinColumn(name = "steam_game_detail_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "steam_game_details_genres",
            joinColumns = @JoinColumn(name = "steam_game_detail_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreEntity> genres = new HashSet<>();

    @OneToMany(mappedBy = "steamGameDetailEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlatformEntity> platforms = new HashSet<>();
}
