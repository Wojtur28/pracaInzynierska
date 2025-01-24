package org.example.pracainzynierska.web.entrypoint;

import com.example.api.UserApi;
import com.example.model.UpdateUser;
import com.example.model.User;
import lombok.AllArgsConstructor;
import org.example.pracainzynierska.core.usecase.user.DeleteUserUseCase;
import org.example.pracainzynierska.core.usecase.user.GetUserUseCase;
import org.example.pracainzynierska.core.usecase.user.GetUsersUseCase;
import org.example.pracainzynierska.core.usecase.user.UpdateUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final GetUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers(Integer page, Integer size) {
        return ResponseEntity.ok(getUsersUseCase.getUsers(page, size));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUser(UUID id) {
        return ResponseEntity.ok(getUserUseCase.getUser(id));
    }

    @Override
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(getUserUseCase.getCurrentUser());
    }

    @Override
    public ResponseEntity<User> updateUser(@PathVariable("userId") UUID userId, User user) {
        return ResponseEntity.ok(updateUserUseCase.updateUser(userId, user));
    }

    @Override
    public ResponseEntity<User> updateCurrentUser(@RequestBody UpdateUser user) {
        return ResponseEntity.ok(updateUserUseCase.updateCurrentUser(user));
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
