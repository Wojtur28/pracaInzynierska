package org.example.pracainzynierska.core.entities.library;

import jakarta.persistence.*;
import lombok.*;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;

@EqualsAndHashCode(callSuper = true, exclude = {"library", "steamGameEntity"})
@Entity
@Table(name = "library_items", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"library_id", "steam_game_id"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryItemEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "game_status", nullable = false)
    private GameStatus gameStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "library_id")
    private LibraryEntity library;

    @ManyToOne(optional = false)
    @JoinColumn(name = "steam_game_id")
    private SteamGameEntity steamGameEntity;

}
