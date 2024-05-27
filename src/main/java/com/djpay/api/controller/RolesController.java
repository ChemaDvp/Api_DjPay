package com.djpay.api.controller;

import com.djpay.api.dto.UserDTO;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.service.RolServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@CrossOrigin
public class RolesController {
    @Autowired
    private final RolServiceI rolServiceI;

    @GetMapping("/buscar")
    public UserDTO getUserByUsername(@RequestParam String username) {
        return rolServiceI.getUserByUsername(username);
    }
}
