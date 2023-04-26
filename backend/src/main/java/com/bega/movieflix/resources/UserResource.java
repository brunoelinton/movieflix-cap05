package com.bega.movieflix.resources;

import com.bega.movieflix.dtos.UserDTO;
import com.bega.movieflix.entities.User;
import com.bega.movieflix.services.AuthService;
import com.bega.movieflix.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    final AuthService authService;

    public UserResource(UserService userService, AuthService authService) {
        this.authService = authService;
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> getCurrentProfile() {
        User user = authService.authenticated();
        UserDTO dto = new UserDTO(user);
        return ResponseEntity.ok().body(dto);
    }
}