package org.example.pracainzynierska.core.web.entrypoint;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }
}
