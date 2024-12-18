package org.example.pracainzynierska.core.entities.steam.genre;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameDetailEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "genres")
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<SteamGameDetailEntity> steamGameDetails = new HashSet<>();
}


