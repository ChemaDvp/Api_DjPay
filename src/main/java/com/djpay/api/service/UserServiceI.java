package com.djpay.api.service;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.Rol;
import com.djpay.api.persistence.model2.User;

import java.util.List;

public interface UserServiceI {
    UserDTO findUserByUsernameDTO(String username);
    List<User> getUsersDj();
    User getUserById(int id);
}
