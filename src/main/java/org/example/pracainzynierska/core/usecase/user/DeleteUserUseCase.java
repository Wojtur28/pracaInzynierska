package org.example.pracainzynierska.core.usecase.user;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Transactional
    public void deleteUser(UUID id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
        entityManager.flush();
    }
}
