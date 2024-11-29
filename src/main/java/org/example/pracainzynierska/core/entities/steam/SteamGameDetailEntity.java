package org.example.pracainzynierska.core.entities.steam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    private String headerImage;

    private String capsuleImage;

    private String website;

    private String developer;

    private String publisher;

    private int requiredAge; //from String ex. "18"

    private String shortDescription;

    private String supportedLanguages;


}
