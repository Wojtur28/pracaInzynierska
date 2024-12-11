package org.example.pracainzynierska.core.entities.steam.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;

import java.util.HashSet;
import java.util.Set;
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

    @Column(unique = true, nullable = false)
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<SteamGameDetailEntity> steamGameDetails = new HashSet<>();
}
