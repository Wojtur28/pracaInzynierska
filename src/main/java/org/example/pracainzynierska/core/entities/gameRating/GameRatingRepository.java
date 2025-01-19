package org.example.pracainzynierska.core.entities.gameRating;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface GameRatingRepository extends JpaRepository<GameRatingEntity, UUID> {

    Page<GameRatingEntity> findBySteamGame_Id(UUID gameId, Pageable pageable);

    List<GameRatingEntity> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
