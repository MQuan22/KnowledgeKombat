package com.example.knowledgekombat.controller;


import com.example.knowledgekombat.exception.ResourceNotFoundException;
import com.example.knowledgekombat.model.User;
import com.example.knowledgekombat.repository.UserRepository;
import com.example.knowledgekombat.security.CurrentUser;
import com.example.knowledgekombat.security.UserPrincipal;
import com.example.knowledgekombat.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/users")
@Api(tags = "User Controller")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @PostMapping("/image")
    @ApiOperation(value = "Upload user image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image uploaded successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<String> uploadUserImage(
                @RequestParam("image") MultipartFile image,
                HttpServletRequest request
        ) {
            try {
                // Save image to database
                userService.saveUserImage(image.getBytes());

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString())
                        .path("/image");

                URI uri = builder.build(true).toUri();

                return ResponseEntity.ok(uri.toString());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image", e);
            }
        // ...

    }

    @GetMapping("/{userId}/image")
    @ApiOperation(value = "Get user image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = byte[].class),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ResponseEntity<byte[]> getUserImage(@PathVariable("userId") Long userId) {
        byte[] image = userService.getUserImage(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }


}
