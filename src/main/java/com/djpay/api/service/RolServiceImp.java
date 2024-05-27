package com.djpay.api.service;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImp implements RolServiceI{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = findUserByUsername(username);
        return convertToDto(user);
    }

    private UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
