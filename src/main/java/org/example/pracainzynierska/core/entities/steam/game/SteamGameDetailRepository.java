package org.example.pracainzynierska.core.entities.steam.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SteamGameDetailRepository extends JpaRepository<SteamGameDetailEntity, Long> {

    Optional<SteamGameDetailEntity> findBySteamGameEntity_Id(UUID steamGameEntityId);

}
