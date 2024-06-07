package com.djpay.api.controller;

import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.User;
import com.djpay.api.persistence.repository2.PeticionRepository;
import com.djpay.api.service.PeticionServiceI;
import com.djpay.api.service.UserServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/peticion")
@RequiredArgsConstructor
@CrossOrigin
public class PeticionController {
    private static final Logger logger = Logger.getLogger(PeticionController.class.getName());

    @Autowired
    private PeticionServiceI peticionServiceI;

    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private PeticionRepository peticionRepository;
    private List<Peticion> listaPeticiones = new ArrayList<>();

    @PostMapping("/enviar/{usuarioId}")
    public String enviarPeticion(@PathVariable int usuarioId, @RequestBody String texto) {
        logger.info("Recibida solicitud para enviar petición para el usuario con ID: " + usuarioId + " con el mensaje: " + texto);
        String resultado = peticionServiceI.enviarPeticion(usuarioId, texto);
        logger.info("Resultado del envío de petición: " + resultado);
        return resultado;
    }

    @PostMapping("/respuesta")
    public String responderPeticion(@PathVariable int peticionId, @RequestParam boolean aceptada) {
        logger.info("Respondiendo petición con ID: " + peticionId + " con estado aceptada: " + aceptada);
        return peticionServiceI.responderPeticion(peticionId, aceptada);
    }

    /*
    @GetMapping("/listaPeticiones")
    public ResponseEntity<List<Peticion>> getAllPeticiones() {
        try {
            logger.info("Obteniendo todas las peticiones");
            List<Peticion> listaPeticiones = peticionServiceI.obtenerPeticiones();
            logger.info("Lista de peticiones obtenida: " + listaPeticiones);
            return ResponseEntity.ok(listaPeticiones);
        } catch (Exception e) {
            logger.severe("Error al obtener las peticiones: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */
}
