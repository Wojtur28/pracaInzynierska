package org.example.pracainzynierska.core.usecase.rating.vote;

import com.example.model.VoteCount;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.VotableType;
import org.example.pracainzynierska.core.entities.gameRating.VoteEntity;
import org.example.pracainzynierska.core.entities.gameRating.VoteRepository;
import org.example.pracainzynierska.core.entities.gameRating.VoteType;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.UserAlreadyVotedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public void vote(UUID votableId, String voteType, VotableType votableType) {
        UserEntity user = userRepository.findByEmail(
                        SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (voteRepository.existsByUser_IdAndVotableIdAndVotableType(user.getId(), votableId, votableType)) {
            throw new UserAlreadyVotedException("User has already voted on this item");
        }

        VoteEntity vote = new VoteEntity();
        vote.setUser(user);
        vote.setVotableId(votableId);
        vote.setVoteType(VoteType.valueOf(voteType.toUpperCase()));
        vote.setVotableType(votableType);

        voteRepository.save(vote);
    }

    public VoteCount countVotes(UUID votableId, VotableType votableType) {
        List<Tuple> results = voteRepository.countVotesByVotableIdAndVotableType(votableId, votableType);

        Map<VoteType, Long> counts = results.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get("type", VoteType.class),
                        tuple -> tuple.get("count", Long.class)
                ));

        int upVotes = Math.toIntExact(counts.getOrDefault(VoteType.UP, 0L));
        int downVotes = Math.toIntExact(counts.getOrDefault(VoteType.DOWN, 0L));

        VoteCount voteCount = new VoteCount();
        voteCount.upvotes(upVotes);
        voteCount.downvotes(downVotes);

        return voteCount;
    }

}
