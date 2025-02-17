package org.example.pracainzynierska.core.entities.gameView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameViewRepository extends JpaRepository<GameViewEntity, UUID> {

    List<GameViewEntity> findByUserIdAndViewDurationGreaterThan(UUID userId, int viewDuration);
}
