package com.djpay.api.security.controllers;

import com.djpay.api.security.model.AuthResponse;
import com.djpay.api.security.model.LoginRequest;
import com.djpay.api.security.model.RegisterRequest;
import com.djpay.api.security.model.UserDTORegister;
import com.djpay.api.security.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Tag(name = "Autenticacion", description = "Operaciones relacionadas con los comentarios")
public class AuthController {

    private final AuthService authMngm;

    @Autowired
    public AuthController(AuthService authMngm) {
        this.authMngm = authMngm;
    }

    /**
     * Endpoint para el registro de usuarios.
     *
     * @param request Datos de registro del usuario.
     * @return Respuesta con el token de autenticación.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<UserDTORegister> register(@RequestBody RegisterRequest request) {

        UserDTORegister response = authMngm.register(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para la autenticación de usuarios.
     *
     * @param request Datos de inicio de sesión del usuario.
     * @return Respuesta con el token de autenticación.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authMngm.login(request));
    }
}
