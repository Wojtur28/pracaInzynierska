package org.example.pracainzynierska.config.security.controller;

import lombok.AllArgsConstructor;
import org.example.pracainzynierska.config.security.dto.SignInResponse;
import org.example.pracainzynierska.config.security.dto.SignInUser;
import org.example.pracainzynierska.config.security.dto.SignUpResponse;
import org.example.pracainzynierska.config.security.dto.SignUpUser;
import org.example.pracainzynierska.config.security.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody SignUpUser signUpUser) {
        try {
            SignUpResponse response = authenticationService.signup(signUpUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInUser signInUser) {
        SignInResponse response = authenticationService.signin(signInUser);
        return ResponseEntity.ok(response);
    }
}
