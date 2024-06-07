package com.djpay.api.service;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.Rol;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.PeticionRepository;
import com.djpay.api.persistence.repository2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserServiceI{
    private final UserRepository userRepository;
    private PeticionRepository peticionRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public UserDTO findUserByUsernameDTO(String username) {
        User user = findUserByUsername(username);

        UserDTO userDTO = new UserDTO();
        if (findUserByUsername(username).getRole() == Rol.Dj) {
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setImgPerfil(user.getImgPerfil());
            return userDTO;
        } else {
            System.out.println("Dj no encontrado");
        }
        return userDTO;
    }

    @Override
    public List<User> getUsersDj() {
        logger.info("Buscando usuarios con rol DJ");
        List<User> users = userRepository.findByRole(Rol.Dj);
        logger.info("Usuarios encontrados con rol DJ: {}", users);
        return users;
    }
}
