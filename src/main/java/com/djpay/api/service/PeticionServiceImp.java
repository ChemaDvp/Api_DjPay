package com.djpay.api.service;

import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.PeticionRepository;
import com.djpay.api.persistence.repository2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PeticionServiceImp implements PeticionServiceI{
    private static final Logger logger = Logger.getLogger(PeticionServiceImp.class.getName());

    @Autowired
    private PeticionRepository peticionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String enviarPeticion(int usuarioId, String texto) {
        logger.info("Iniciando el envío de petición para el usuario con ID: " + usuarioId);
        User usuario = userRepository.findById(usuarioId).orElse(null);
        if (usuario == null || usuario.getRole() == null || usuario.getRole().equals("Usuario")) {
            logger.warning("Usuario no encontrado o no es el rol adecuado para el ID: " + usuarioId);
            return "Usuario no encontrado o no es el rol adecuado";
        }
        Peticion peticion = new Peticion();
        peticion.setAuthor(usuario);
        peticion.setContenido(texto);
        System.out.println(texto);
        peticion.setEstado(false);
        logger.info("MIRA AQUI: "+ peticion.getContenido());
        peticionRepository.save(peticion);
        logger.info("Petición enviada correctamente para el usuario con ID: " + usuarioId);
        return "Petición enviada";
    }

    @Override
    public String responderPeticion(int peticionId, boolean aceptada) {
        logger.info("Iniciando respuesta para la petición con ID: " + peticionId + " aceptada: " + aceptada);
        Peticion peticion = peticionRepository.findById(peticionId).orElse(null);
        if (peticion == null) {
            logger.warning("Petición no encontrada con ID: " + peticionId);
            return "Petición no encontrada";
        }
        peticion.setEstado(aceptada);
        peticionRepository.save(peticion);
        logger.info("Respuesta registrada correctamente para la petición con ID: " + peticionId);
        return "Respuesta registrada";
    }


    @Override
    public List<Peticion> obtenerPeticiones(long id) {
        logger.info("Obteniendo todas las peticiones");
        List<Peticion> peticiones = userRepository.getUserById(id).getPeticiones();
        return peticiones;
    }


    /*
    @Override
    public List<Peticion> obtenerPeticiones(long id) {
        logger.info("Obteniendo todas las peticiones");
        User user = userRepository.getUserById(id);
        if (user != null) {
            List<Peticion> peticiones = user.getPeticiones();
            return peticiones != null ? peticiones : new ArrayList<>();
        } else {
            logger.warning("No se encontró ningún usuario con el ID proporcionado: " + id);
            return new ArrayList<>();
        }
    }

     */
}
