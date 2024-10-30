package org.example.pracainzynierska.core.entities.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserGameRatingRepository extends JpaRepository<UserGameRating, UUID> {
}
