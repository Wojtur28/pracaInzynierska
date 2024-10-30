package org.example.pracainzynierska.core.entities.screenshot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;

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

    @Column(name = "game_id")
    private Long gameId;

    private int height;

    private String imageId;

    private String url;

    private int width;
}
