package org.example.pracainzynierska.core.entities.steam.review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.user.SteamUserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "steam_reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "steam_reviews")
public class SteamReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "review_id", unique = true, nullable = false)
    private String reviewId;

    @Column(name = "user_steam_id", nullable = false)
    private String userSteamId;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "timestamp_created", nullable = false)
    @EqualsAndHashCode.Exclude
    private LocalDateTime timestampCreated;

    @Column(name = "votes_up", nullable = false)
    private Integer votesUp;

    @Column(name = "votes_down", nullable = false)
    private Integer votesDown;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "steam_user_id")
    @EqualsAndHashCode.Exclude
    private SteamUserEntity steamUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_id", referencedColumnName = "app_id", nullable = false)
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SteamGameEntity game;

    @Override
    public String toString() {
        return "SteamReviewEntity{" +
                "userSteamId='" + userSteamId + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", timestampCreated=" + timestampCreated +
                ", votesUp=" + votesUp +
                ", votesDown=" + votesDown +
                ", reviewId='" + reviewId + '\'' +
                ", id=" + id +
                '}';
    }
}
