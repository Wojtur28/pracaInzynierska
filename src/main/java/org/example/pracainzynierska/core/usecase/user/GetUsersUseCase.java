package org.example.pracainzynierska.core.usecase.user;

import com.example.model.User;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.mapper.UserMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<User> getUsers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<UserEntity> users = userRepository.findAll(pageable).getContent();

        return userMapper.toDto(users);
    }

}
