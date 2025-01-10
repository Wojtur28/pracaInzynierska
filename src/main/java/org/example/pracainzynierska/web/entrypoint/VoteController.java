package org.example.pracainzynierska.web.entrypoint;

import com.example.api.VotesApi;
import com.example.model.Vote;
import com.example.model.VoteCount;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.rating.vote.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class VoteController implements VotesApi {

    private final VoteService voteService;

    @Override
    public ResponseEntity<Void> voteOnGameRating(@PathVariable("ratingId") UUID ratingId, @RequestBody Vote vote) {
        voteService.vote(ratingId, vote.getVoteType());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> voteOnGameRatingAnswer(@PathVariable("answerId") UUID answerId, @RequestBody Vote vote) {
        voteService.vote(answerId, vote.getVoteType());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<VoteCount> getVotesForGameRating(@PathVariable("ratingId") UUID ratingId) {
        return ResponseEntity.ok(voteService.countVotes(ratingId));
    }

    @Override
    public ResponseEntity<VoteCount> getVotesForGameRatingAnswer(@PathVariable("answerId") UUID answerId) {
        return ResponseEntity.ok(voteService.countVotes(answerId));
    }

}
