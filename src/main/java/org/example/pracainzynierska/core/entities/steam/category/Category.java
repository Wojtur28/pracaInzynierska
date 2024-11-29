package org.example.pracainzynierska.core.entities.steam.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.SteamGameDetailEntity;

import java.util.UUID;

@Entity(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "steam_game_detail_entity_id")
    private SteamGameDetailEntity steamGameDetailEntity;

    private String description;
}
