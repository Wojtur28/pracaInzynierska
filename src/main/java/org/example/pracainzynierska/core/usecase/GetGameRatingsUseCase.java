package org.example.pracainzynierska.core.usecase;

import com.example.model.GameRating;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.example.pracainzynierska.mapper.GameRatingMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetGameRatingsUseCase {

    private final GameRatingRepository gameRatingRepository;
    private final GameRatingMapper gameRatingMapper;

    public List<GameRating> getGameRatings(UUID gameId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GameRating> gameRatings = gameRatingRepository.findBySteamGame_Id(gameId, pageable)
                .map(gameRatingMapper::toModel);

        return gameRatings.getContent();
    }
}
