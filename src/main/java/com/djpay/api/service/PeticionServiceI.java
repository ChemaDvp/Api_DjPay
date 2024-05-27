package com.djpay.api.service;

public interface PeticionServiceI {
    String enviarPeticion(int usuarioId, String texto);
    String responderPeticion(int peticionId, boolean aceptada);
}
