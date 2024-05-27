package com.djpay.api.controller;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.service.UserServiceI;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    @Autowired
    private final UserServiceI userServiceI;

    @GetMapping("/public/{username}")
    @Operation(summary = "Obtener usuario por username")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userServiceI.findUserByUsernameDTO(username);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users/dj")
    public List<User> getUsersWithDjRole() {
        return userServiceI.getUsersDj();
    }
}
