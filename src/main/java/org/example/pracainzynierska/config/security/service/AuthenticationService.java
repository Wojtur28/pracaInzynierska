package org.example.pracainzynierska.config.security.service;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.config.security.dto.SignInResponse;
import org.example.pracainzynierska.config.security.dto.SignInUser;
import org.example.pracainzynierska.config.security.dto.SignUpResponse;
import org.example.pracainzynierska.config.security.dto.SignUpUser;
import org.example.pracainzynierska.core.entities.user.Role;
import org.example.pracainzynierska.core.entities.user.UserEntity;
import org.example.pracainzynierska.core.entities.user.UserRepository;
import org.example.pracainzynierska.core.usecase.library.CreateLibraryUseCase;
import org.example.pracainzynierska.exception.UserAlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CreateLibraryUseCase createLibraryUseCase;


    public SignUpResponse signup(SignUpUser input) {
        if (userRepository.findByEmail(input.email()).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(input.email());
        user.setFirstName(input.firstName());
        user.setLastName(input.lastName());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setGender(input.gender());
        user.setDateOfBirth(input.dateOfBirth());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedBy(user);
        user.setModifiedBy(user);

        UserEntity savedUser = userRepository.save(user);
        createLibraryUseCase.createLibraryForUser(savedUser);
        return new SignUpResponse("User created successfully");
    }

    public SignInResponse signin(SignInUser input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()
                )
        );

        UserEntity authenticatedUser = userRepository.findByEmail(input.email())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(authenticatedUser, authenticatedUser.getId());

        return new SignInResponse(jwtToken, Math.toIntExact(jwtService.getExpirationTime()));
    }
}

