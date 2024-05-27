package com.djpay.api.service;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.Rol;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserServiceI{
    private final UserRepository userRepository;

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
            userDTO.setId(user.getId());
            userDTO.setNombre(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setImgPerfil(user.getImgPerfil());
            //userDTO.setPosts(user.getPosts());
            return userDTO;
        } else {
            System.out.println("Dj no encontrado");
        }
        return userDTO;
    }

    @Override
    public List<User> getUsersDj() {
        return userRepository.findByRole(Rol.Dj);
    }
}
