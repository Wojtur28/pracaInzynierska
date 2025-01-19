package org.example.pracainzynierska.web.entrypoint;

import com.example.api.VotesApi;
import com.example.model.Vote;
import com.example.model.VoteCount;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.gameRating.VotableType;
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
    public ResponseEntity<Void> vote(
            @PathVariable("votableType") String votableTypeStr,
            @PathVariable("votableId") UUID votableId,
            @RequestBody Vote vote) {
        VotableType votableType = VotableType.valueOf(votableTypeStr.toUpperCase());
        voteService.vote(votableId, vote.getVoteType(), votableType);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<VoteCount> getVoteCount(
            @PathVariable("votableType") String votableTypeStr,
            @PathVariable("votableId") UUID votableId) {
        VotableType votableType = VotableType.valueOf(votableTypeStr.toUpperCase());
        VoteCount voteCount = voteService.countVotes(votableId, votableType);
        return ResponseEntity.ok(voteCount);
    }

}
