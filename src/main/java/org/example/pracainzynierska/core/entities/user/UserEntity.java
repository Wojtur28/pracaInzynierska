package org.example.pracainzynierska.core.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.pracainzynierska.core.entities.BaseEntity;
import org.example.pracainzynierska.core.entities.game.GameEntity;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    Set<Role> roles;


    @ManyToMany
    @JoinTable(
            name = "user_game",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<GameEntity> playedGames = new HashSet<>();

}
