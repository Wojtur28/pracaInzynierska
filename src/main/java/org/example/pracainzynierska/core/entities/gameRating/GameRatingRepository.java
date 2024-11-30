package org.example.pracainzynierska.core.entities.gameRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRatingRepository extends JpaRepository<GameRatingEntity, UUID> {

}
