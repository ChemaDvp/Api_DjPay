package com.djpay.api.security.services;

import com.djpay.api.security.model.AuthResponse;
import com.djpay.api.security.model.LoginRequest;
import com.djpay.api.security.model.RegisterRequest;
import com.djpay.api.security.model.UserDTORegister;


public interface AuthServiceI {
    AuthResponse login(LoginRequest request);
    UserDTORegister register(RegisterRequest request);
}
