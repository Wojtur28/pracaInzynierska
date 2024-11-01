package org.example.pracainzynierska.core.entities.screenshot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScreenshotRepository extends JpaRepository<ScreenshotEntity, UUID> {

    Optional<ScreenshotEntity> findByApiId(Long apiId);
}
