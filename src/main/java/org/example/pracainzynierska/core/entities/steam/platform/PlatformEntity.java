package org.example.pracainzynierska.core.entities.steam.platform;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;

import java.util.UUID;

@Entity(name = "platforms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlatformEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "steam_game_detail_entity_id")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private SteamGameDetailEntity steamGameDetailEntity;

    private String name;

    private Boolean isSupport;
}
