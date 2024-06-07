package com.djpay.api.controller;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.UserRepository;
import com.djpay.api.service.PeticionServiceI;
import com.djpay.api.service.UserServiceI;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    @Autowired
    private final UserServiceI userServiceI;

    @Autowired
    private final PeticionServiceI peticionServiceI;

    @Autowired
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/public/{username}")
    @Operation(summary = "Obtener usuario por username")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        UserDTO userDTO = userServiceI.findUserByUsernameDTO(username);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/users/dj")
    public List<UserDTO> getUsersWithDjRole() {
        logger.info("Recibiendo solicitud para obtener usuarios con rol DJ");
        List<User> users = userServiceI.getUsersDj();
        List<UserDTO> userDTOs = new ArrayList<>();

        for(User user : users) {
            logger.info("Usuario con rol DJ: {}", user.getName());
            // Creamos un UserDTO con solo el username
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getName());
            userDTO.setId(user.getId());
            // Agregamos el UserDTO a la lista
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @GetMapping("/peticiones/{id}")
    @Operation(summary = "Obtener todas las peticiones para un Dj")
    public ResponseEntity<List<Peticion>> getAllPeticiones(@PathVariable long id) {
        List<Peticion> peticiones = peticionServiceI.obtenerPeticiones(id);
        if (peticiones.isEmpty()) {
            logger.info("No existen peticiones para este Dj.");
        } else {
            logger.info("Peticiones encontradas: {}", peticiones);
        }
        return ResponseEntity.ok(peticiones);
    }

    @GetMapping("/details")
    public ResponseEntity<User> getUserDetails() {
        // Obtenemos la información del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().build(); // No hay usuario autenticado
        }
        // El usuario está autenticado, obtenemos sus detalles
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    /*
    @GetMapping("/peticiones")
    @Operation(summary = "Obtener todas las peticiones")
    public ResponseEntity<List<Peticion>> getAllPeticiones() {
        List<Peticion> peticiones = peticionServiceI.obtenerPeticiones();
        if (peticiones.isEmpty()) {
            logger.info("No se encontraron peticiones.");
        } else {
            logger.info("Peticiones encontradas: {}", peticiones);
        }
        return ResponseEntity.ok(peticiones);
    }
     */
}
