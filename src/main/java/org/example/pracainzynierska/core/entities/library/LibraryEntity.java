package org.example.pracainzynierska.core.entities.library;

import jakarta.persistence.*;
import lombok.*;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = "libraryItems")
@Entity
@Table(name = "libraries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryEntity extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true)
    private UserEntity user;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LibraryItemEntity> libraryItems = new HashSet<>();
}
