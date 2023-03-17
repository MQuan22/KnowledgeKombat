package com.example.knowledgekombat.controller;

import com.example.knowledgekombat.repository.UserRepository;
import com.example.knowledgekombat.exception.BadRequestException;
import com.example.knowledgekombat.model.AuthProvider;
import com.example.knowledgekombat.model.User;
import com.example.knowledgekombat.payload.ApiResponse;
import com.example.knowledgekombat.payload.AuthResponse;
import com.example.knowledgekombat.payload.LoginRequest;
import com.example.knowledgekombat.payload.SignUpRequest;
import com.example.knowledgekombat.security.TokenProvider;
import com.example.knowledgekombat.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        RedisUtils redisUtils = new RedisUtils();

        // Try to retrieve the authentication token from Redis cache
        String authToken = redisUtils.GetValue(loginRequest.getEmail());
        if (authToken != null) {
            // If the authentication token exists in Redis cache, return it
            return ResponseEntity.ok(new AuthResponse(authToken));
        }


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        authToken = tokenProvider.createToken(authentication);

        // Store the authentication token in Redis cache
        redisUtils.SetKeyValue(loginRequest.getEmail(), authToken);
        redisUtils.SetExpire(loginRequest.getEmail(), 3600); // expire after 1 hour


        return ResponseEntity.ok(new AuthResponse(authToken));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        Date date = new Date();
        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setStatus(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        user.setWallet(0);
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
