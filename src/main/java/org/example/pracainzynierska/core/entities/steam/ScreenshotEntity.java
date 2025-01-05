package org.example.pracainzynierska.core.entities.steam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;

import java.util.UUID;

@Entity(name = "screenshots")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ScreenshotEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "path_thumbnail", nullable = false, columnDefinition = "TEXT")
    private String pathThumbnail;

    @Column(name = "path_full", nullable = false, columnDefinition = "TEXT")
    private String pathFull;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "steam_game_detail_entity_id", nullable = false)
    private SteamGameDetailEntity steamGameDetailEntity;
}

