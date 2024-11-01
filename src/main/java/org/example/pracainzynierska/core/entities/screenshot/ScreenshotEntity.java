package org.example.pracainzynierska.core.entities.screenshot;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.game.GameEntity;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
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

    @Column(nullable = false)
    private String imageId;

    private String url;

    private int width;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @Override
    public String toString() {
        return "ScreenshotEntity{" +
                "width=" + width +
                ", url='" + url + '\'' +
                ", imageId='" + imageId + '\'' +
                ", height=" + height +
                ", checksum=" + checksum +
                ", animated=" + animated +
                ", alphaChannel=" + alphaChannel +
                ", apiId=" + apiId +
                '}';
    }
}
