package com.djpay.api.service;

import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.PeticionRepository;
import com.djpay.api.persistence.repository2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeticionServiceImp implements PeticionServiceI{
    @Autowired
    private PeticionRepository peticionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String enviarPeticion(int usuarioId, String texto) {
        User usuario = userRepository.findById(usuarioId).orElse(null);
        if (usuario == null && usuario.getRole() == null && usuario.getRole().equals("Usuario")) {
            return "Usuario no encontrado o no es del rol adecuado";
        }
        Peticion peticion = new Peticion();
        peticion.setAuthorId(usuario);
        peticion.setContenido(texto);
        peticion.setEstado("pendiente");
        peticionRepository.save(peticion);
        return "Petición enviada";
    }

    @Override
    public String responderPeticion(int peticionId, boolean aceptada) {
        Peticion peticion = peticionRepository.findById(peticionId).orElse(null);
        if (peticion == null) {
            return "Petición no encontrada";
        }
        peticion.setEstado(aceptada ? "aceptada" : "rechazada");
        peticionRepository.save(peticion);
        return "Respuesta registrada";
    }
}
