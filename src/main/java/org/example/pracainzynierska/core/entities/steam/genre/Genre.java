package org.example.pracainzynierska.core.entities.steam.genre;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;

import java.util.UUID;

@Entity(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "steam_game_detail_entity_id")
    private SteamGameDetailEntity steamGameDetailEntity;

    private String description;
}
