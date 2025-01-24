package org.example.pracainzynierska.core.usecase.user;

import com.example.model.UpdateUser;
import com.example.model.User;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.user.Role;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.exception.UserNotFoundException;
import org.example.pracainzynierska.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User updateUser(UUID userId, User user) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setGender(userMapper.mapStringToGender(user.getGender()));
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setRoles(
                user.getRoles().stream()
                        .map(Role::valueOf)
                        .collect(Collectors.toSet())
        );

        return userMapper.toDto(userRepository.save(userEntity));
    }

    public User updateCurrentUser(UpdateUser user) {
        UserEntity userEntity = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setGender(userMapper.mapStringToGender(user.getGender()));


        return userMapper.toDto(userRepository.save(userEntity));
    }

}
