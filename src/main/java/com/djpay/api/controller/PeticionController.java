package com.djpay.api.controller;

import com.djpay.api.service.PeticionServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/peticion")
@RequiredArgsConstructor
@CrossOrigin
public class PeticionController {
    @Autowired
    private PeticionServiceI peticionServiceI;

    @PostMapping("/enviar")
    public String enviarPeticion(@RequestParam int usuarioId, @RequestParam String texto) {
        return peticionServiceI.enviarPeticion(usuarioId, texto);
    }

    @PostMapping("/respuesta")
    public String responderPeticion(@RequestParam int peticionId, @RequestParam boolean aceptada) {
        return peticionServiceI.responderPeticion(peticionId, aceptada);
    }
}
