package org.example.pracainzynierska.core.usecase;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetGameRatingsUseCase {

    private final GameRatingRepository gameRatingRepository;

}
