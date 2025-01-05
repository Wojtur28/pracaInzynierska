package org.example.pracainzynierska.core.usecase.rating;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.VotableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VotingService {

    public <T extends VotableEntity> T vote(
            T entity,
            String voteType,
            JpaRepository<T, UUID> repository) {

        if (voteType == null || (!voteType.equalsIgnoreCase("UP") && !voteType.equalsIgnoreCase("DOWN"))) {
            throw new IllegalArgumentException("Invalid vote type: " + voteType);
        }

        if (voteType.equalsIgnoreCase("UP")) {
            entity.setVotesUp(entity.getVotesUp() + 1);
        } else {
            entity.setVotesDown(entity.getVotesDown() + 1);
        }

        return repository.save(entity);
    }
}
