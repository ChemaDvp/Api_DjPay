package com.djpay.api.service;

import com.djpay.api.persistence.model2.Peticion;

import java.util.List;

public interface PeticionServiceI {
    String enviarPeticion(int usuarioId, String texto);
    String responderPeticion(int peticionId, boolean aceptada);
    List<Peticion> obtenerPeticiones(long id);
}
