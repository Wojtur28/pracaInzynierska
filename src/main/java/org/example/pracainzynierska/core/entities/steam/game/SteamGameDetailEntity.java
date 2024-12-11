package org.example.pracainzynierska.core.entities.steam.game;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.category.Category;
import org.example.pracainzynierska.core.entities.steam.genre.Genre;
import org.example.pracainzynierska.core.entities.steam.platform.Platform;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "steam_game_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SteamGameDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "steam_game_entity_id")
    private SteamGameEntity steamGameEntity;

    @Lob
    @Column(columnDefinition = "text")
    private String headerImage;

    @Lob
    @Column(columnDefinition = "text")
    private String capsuleImage;

    @Lob
    @Column(columnDefinition = "text")
    private String website;

    @Lob
    @Column(columnDefinition = "text")
    private String developer;

    @Lob
    @Column(columnDefinition = "text")
    private String publisher;

    private int requiredAge; //from String ex. "18"

    @Lob
    @Column(columnDefinition = "text")
    private String shortDescription;

    @Lob
    @Column(columnDefinition = "text")
    private String supportedLanguages;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "steam_game_details_categories",
            joinColumns = @JoinColumn(name = "steam_game_detail_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "steam_game_details_genres",
            joinColumns = @JoinColumn(name = "steam_game_detail_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "steamGameDetailEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Platform> platforms = new HashSet<>();
}

