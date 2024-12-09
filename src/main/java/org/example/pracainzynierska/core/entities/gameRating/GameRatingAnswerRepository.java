package org.example.pracainzynierska.core.entities.gameRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameRatingAnswerRepository extends JpaRepository<GameRatingAnswerEntity, UUID> {

    List<GameRatingAnswerEntity> findBygameRatingEntity_Id(UUID gameRatingEntityId);
}
