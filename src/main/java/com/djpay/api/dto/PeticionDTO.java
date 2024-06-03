package com.djpay.api.dto;

import com.djpay.api.persistence.model2.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PeticionDTO {
    //ATRIBUTOS
    public String id;
    public String contenido;
    public User auhthorId;

}
