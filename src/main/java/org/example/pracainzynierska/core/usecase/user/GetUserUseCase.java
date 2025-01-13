package org.example.pracainzynierska.core.usecase.user;

import com.example.model.User;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.UserNotFoundException;
import org.example.pracainzynierska.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GetUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUser(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("User with id " + id + " not found"));
        return userMapper.toDto(userEntity);
    }

    public User getCurrentUser() {
        UserEntity userEntity = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()
                -> new UserNotFoundException("User not found"));

        return userMapper.toDto(userEntity);
    }
}
