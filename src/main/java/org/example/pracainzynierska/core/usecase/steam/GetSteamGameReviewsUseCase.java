package org.example.pracainzynierska.core.usecase.steam;

import com.example.model.SteamReview;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.review.SteamReviewRepository;
import org.example.pracainzynierska.mapper.SteamReviewMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GetSteamGameReviewsUseCase {


    private final SteamReviewRepository steamReviewRepository;
    private final SteamReviewMapper steamReviewMapper;

    public List<SteamReview> getGameReviews(UUID gameId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SteamReview> steamReviews = steamReviewRepository.findByGameId(gameId, pageable)
                .map(steamReviewMapper::toModel);

        return steamReviews.getContent();
    }
}
