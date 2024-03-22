package com.application.rest.controller;


import com.application.rest.domain.Nodos.*;
import com.application.rest.domain.Nodos.NodoEntity;
import jakarta.transaction.Transactional;

import com.application.rest.domain.Nodos.NodoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/nodos")
public class NodoController {

    @Autowired
    private NodoRepository nodoRepository;

    @PostMapping
    public ResponseEntity<NodoDtoG> guardarNodo(@RequestBody @Valid NodoDtoP nodoDtoP, UriComponentsBuilder builder){

        System.out.println("El Post Fue Correcto (Se a guardado correctamente) ");

        NodoEntity nodo = nodoRepository.save(new NodoEntity(nodoDtoP)); // Se usa el repository para guardar los datos que vienen

        NodoDtoG nodoDtoG = new NodoDtoG(nodo.getNodo_id(),
                nodo.getNodo(), nodo.getFase(), nodo.getZona());

        URI url = builder.path("/nodos/{id}").buildAndExpand(nodo.getNodo_id()).toUri();

        return ResponseEntity.created(url).body(nodoDtoG);

    }

}
