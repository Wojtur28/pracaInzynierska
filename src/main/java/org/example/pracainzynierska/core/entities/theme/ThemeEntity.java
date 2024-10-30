package org.example.pracainzynierska.core.entities.theme;

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
@Table(name = "themes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Long apiId;

    private String name;

    private String slug;

    private String url;

    @Column(unique = true)
    private UUID checksum;
}
