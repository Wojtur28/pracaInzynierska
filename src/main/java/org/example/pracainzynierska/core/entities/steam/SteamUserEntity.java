package org.example.pracainzynierska.core.entities.steam;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "steam_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SteamUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "steam_id", unique = true, nullable = false)
    private String steamId;

    @OneToMany(mappedBy = "steamUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<SteamReviewEntity> steamReviews = new HashSet<>();
}

