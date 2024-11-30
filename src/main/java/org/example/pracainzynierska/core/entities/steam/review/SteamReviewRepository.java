package org.example.pracainzynierska.core.entities.steam.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SteamReviewRepository extends JpaRepository<SteamReviewEntity, UUID> {

    Page<SteamReviewEntity> findByGameId(UUID gameId, Pageable pageable);
}
