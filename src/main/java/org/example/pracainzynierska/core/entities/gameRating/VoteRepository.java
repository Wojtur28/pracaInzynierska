package org.example.pracainzynierska.core.entities.gameRating;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, UUID> {

    boolean existsByUser_IdAndVotableIdAndVotableType(UUID userId, UUID votableId, VotableType votableType);

    @Query("SELECT v.voteType AS type, COUNT(v) AS count " +
            "FROM votes v " +
            "WHERE v.votableId = :votableId AND v.votableType = :votableType " +
            "GROUP BY v.voteType")
    List<Tuple> countVotesByVotableIdAndVotableType(@Param("votableId") UUID votableId,
                                                    @Param("votableType") VotableType votableType);
}
