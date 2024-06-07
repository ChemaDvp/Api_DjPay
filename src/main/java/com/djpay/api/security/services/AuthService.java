package com.djpay.api.security.services;


import com.djpay.api.persistence.model2.Rol;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.UserRepository;
import com.djpay.api.security.model.AuthResponse;
import com.djpay.api.security.model.LoginRequest;
import com.djpay.api.security.model.RegisterRequest;
import com.djpay.api.security.model.UserDTORegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceI {

    private final UserRepository userRepo;

    private final JWTServiceI jwtMngm;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthService(UserRepository userRepo, JWTServiceI jwtMngm, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtMngm = jwtMngm;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param request La solicitud de registro.
     * @return La respuesta de autenticación.
     */
    @Override
    public UserDTORegister register(RegisterRequest request) {
        User user = new User(request.getUsername(),
                request.getEmail(),passwordEncoder.encode(request.getPassword()), request.getRol());

        userRepo.save(user);

        return convertToDtoRegister(user);
    }

    /**
     * Inicia sesión en el sistema.
     *
     * @param request La solicitud de inicio de sesión.
     * @return La respuesta de autenticación.
     */
    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        User user = userRepo.findByEmail(request.getEmail());

        var jwtToken = jwtMngm.getToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Convierte un objeto User en un objeto UserDto.
     *
     * @param user Objeto User a convertir.
     * @return Objeto UserDto convertido.
     */
    private UserDTORegister convertToDtoRegister(User user) {
        UserDTORegister userDto = new UserDTORegister();
        userDto.setUsername(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRol(user.getRole());

        return userDto;
    }

}
