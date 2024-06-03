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

@RestController
@RequestMapping("/api/v1/peticion")
@RequiredArgsConstructor
@CrossOrigin
public class PeticionController {
    @Autowired
    private PeticionServiceI peticionServiceI;

    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private PeticionRepository peticionRepository;
    private List<Peticion> listaPeticiones = new ArrayList<>();

    @PostMapping("/enviar/{usuarioId}")
    public String enviarPeticion(@PathVariable int usuarioId, @RequestBody String texto) {
        User usuario = userServiceI.getUserById(usuarioId);
        if (usuario == null || !"Usuario".equals(usuario.getRole())) {
            return "Usuario no encontrado o no es el rol adecuado";
        }
        Peticion peticion = new Peticion();
        peticion.setAuthor(usuario);
        peticion.setContenido(texto);
        peticion.setEstado(false);
        peticionRepository.save(peticion);
        listaPeticiones.add(peticion);
        return "Petición enviada";
    }

    @PostMapping("/respuesta")
    public String responderPeticion(@PathVariable int peticionId, @RequestParam boolean aceptada) {
        return peticionServiceI.responderPeticion(peticionId, aceptada);
    }

    /*
    @GetMapping("/listaPeticiones")
    public ResponseEntity<List<Peticion>> getAllPeticiones() {
        try {
            List<Peticion> listaPeticiones = userServiceI.getAllPeticiones();
            return ResponseEntity.ok(listaPeticiones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
     */

    @GetMapping("/listaPeticiones")
    public ResponseEntity<List<Peticion>> getAllPeticiones() {
        try {
            List<Peticion> listaPeticiones = peticionServiceI.obtenerPeticiones();
            return ResponseEntity.ok(listaPeticiones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
