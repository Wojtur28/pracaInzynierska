package org.example.pracainzynierska.core.entities.theme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, UUID> {

    Optional<ThemeEntity> findByApiId(Long apiId);
}
