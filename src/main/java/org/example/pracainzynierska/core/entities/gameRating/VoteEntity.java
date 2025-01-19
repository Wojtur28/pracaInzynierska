package org.example.pracainzynierska.core.entities.gameRating;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.user.UserEntity;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "votable_id", nullable = false)
    private UUID votableId;

    @Enumerated(EnumType.STRING)
    @Column(name = "votable_type", nullable = false)
    private VotableType votableType;
}
