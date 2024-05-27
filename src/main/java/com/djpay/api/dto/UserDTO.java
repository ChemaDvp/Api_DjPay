package com.djpay.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
    //ATRIBUTOS
    public int id;
    public String nombre;
    public String email;
    public String imgPerfil;
}
