package org.example.pracainzynierska.core.entities.steam.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SteamGameRepository extends JpaRepository<SteamGameEntity, UUID> {
}
