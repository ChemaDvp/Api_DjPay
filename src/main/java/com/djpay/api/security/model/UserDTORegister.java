package com.djpay.api.security.model;

import com.djpay.api.persistence.model2.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTORegister implements Serializable {

    /** Nombre de usuario **/
    private String username;

    /** Email del usuario **/
    private String email;

    /** Contraseña del usuario **/
    private String password;

    private Rol rol;
}
