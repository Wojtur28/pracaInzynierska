package org.example.pracainzynierska.core.entities.steam.game;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SteamGameRepository extends JpaRepository<SteamGameEntity, UUID> {

    @EntityGraph(attributePaths = {"steamGameDetailEntity.categories", "steamGameDetailEntity.genres", "steamGameDetailEntity.platforms"})
    List<SteamGameEntity> findBySteamGameDetailEntityPlatformsName(String platformName, Pageable pageable);
}
