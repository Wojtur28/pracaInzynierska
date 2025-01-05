package org.example.pracainzynierska.core.usecase.rating;

import com.example.model.GameRating;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.GameRatingRepository;
import org.example.pracainzynierska.mapper.GameRatingMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetGameRatingsUseCase {

    private final GameRatingRepository gameRatingRepository;
    private final GameRatingMapper gameRatingMapper;

    public List<GameRating> getGameRatings(UUID gameId, int page, int size, String sort) {
        Sort sortCriteria = Sort.by("createdAt").descending();

        if (sort != null) {
            switch (sort) {
                case "votesUp_desc":
                    sortCriteria = Sort.by("votesUp").descending();
                    break;
                case "votesUp_asc":
                    sortCriteria = Sort.by("votesUp").ascending();
                    break;
                case "createdAt_asc":
                    sortCriteria = Sort.by("createdAt").ascending();
                    break;
                case "votesDown_desc":
                    sortCriteria = Sort.by("votesDown").descending();
                    break;
                case "votesDown_asc":
                    sortCriteria = Sort.by("votesDown").ascending();
                    break;
                default:
                    break;
            }
        }

        Pageable pageable = PageRequest.of(page, size, sortCriteria);
        return gameRatingRepository.findBySteamGame_Id(gameId, pageable)
                .map(gameRatingMapper::toDto)
                .getContent();
    }
}
