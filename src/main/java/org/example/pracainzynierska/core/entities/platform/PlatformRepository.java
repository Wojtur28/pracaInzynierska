package org.example.pracainzynierska.core.entities.platform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, UUID> {

    Optional<PlatformEntity> findByApiId(Long apiId);

}
