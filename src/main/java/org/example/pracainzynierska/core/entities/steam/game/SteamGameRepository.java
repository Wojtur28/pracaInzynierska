package org.example.pracainzynierska.core.entities.steam.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SteamGameRepository extends JpaRepository<SteamGameEntity, UUID>, JpaSpecificationExecutor<SteamGameEntity> {

//    @Query("SELECT g FROM SteamGameEntity g WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :search, '%'))")
//    List<SteamGameEntity> searchByName(@Param("search") String search);
}
