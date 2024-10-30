package org.example.pracainzynierska.core.entities.screenshot;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.game.GameEntity;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "screenshots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenshotEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Long apiId;

    private boolean alphaChannel;

    private boolean animated;

    @Column(unique = true)
    private UUID checksum;

    private int height;

    private String imageId;

    private String url;

    private int width;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;
}
