package org.example.pracainzynierska.core.entities.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, UUID> {
    Optional<LibraryEntity> findByUser_Id(UUID userId);
}
