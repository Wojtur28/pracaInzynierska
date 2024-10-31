package org.example.pracainzynierska.core.entities.platform;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatformEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private Long apiId;

    @Column(nullable = false)
    private String name;
}
