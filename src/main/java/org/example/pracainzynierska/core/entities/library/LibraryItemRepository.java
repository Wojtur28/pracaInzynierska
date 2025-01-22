package org.example.pracainzynierska.core.entities.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItemEntity, UUID> {
}
