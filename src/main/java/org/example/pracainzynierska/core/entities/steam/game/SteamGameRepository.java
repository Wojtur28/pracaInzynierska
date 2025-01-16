package org.example.pracainzynierska.core.entities.steam.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SteamGameRepository extends JpaRepository<SteamGameEntity, UUID>, JpaSpecificationExecutor<SteamGameEntity> {

    List<SteamGameEntity> findByReleaseDateBetween(LocalDate releaseDate, LocalDate releaseDate2);

}
