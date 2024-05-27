package com.djpay.api.service;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.User;

import java.util.List;

public interface RolServiceI {
    UserDTO getUserByUsername(String username);
}
