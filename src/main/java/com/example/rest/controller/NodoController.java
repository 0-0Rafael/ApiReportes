package com.example.rest.controller;

import com.example.rest.domain.Nodos.*;
import com.example.rest.domain.Nodos.NodoEntity;
import jakarta.transaction.Transactional;

import com.example.rest.domain.Nodos.NodoRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<Page<NodoDtoG>> listadoNodo(@PageableDefault (size = 40 )Pageable pageable){
        return ResponseEntity.ok(nodoRepository.findAll(pageable).map(NodoDtoG::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarNodo(@RequestBody @Valid NodoDtoPut actualizarNodo){

        NodoEntity nodo = nodoRepository.getReferenceById(actualizarNodo.id());
        nodo.actualizarNodo(actualizarNodo);

        return ResponseEntity.ok(new NodoDtoG(nodo.getNodo_id(),nodo.getNodo(),nodo.getFase(), nodo.getZona()));

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarNodo(@PathVariable Long id){
        NodoEntity nodo = nodoRepository.getReferenceById(id);

        nodoRepository.delete(nodo);
        System.out.println("Nodo Eliminado Correctamente");

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "nodos/{nodo}")
    public ResponseEntity obtenerNodoporNombre(@PathVariable("nodo") Nodo nodo, @PageableDefault(size = 40) Pageable page){

        NodoEntity nodo1 = nodoRepository.findByNodo(nodo);

        if (nodo1 == null){
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(nodo1);

    }

    @GetMapping(path = "fase/{fase}")
    public ResponseEntity<List<NodoEntity>> obtenerNodoPorFase(@PathVariable("fase") Fase fase, @PageableDefault(size = 40) Pageable page){

        if (fase != null){
            return ResponseEntity.ok(nodoRepository.findByFase(fase));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "zona/{zona}")
    public ResponseEntity<List<NodoEntity>> obtenerNodoPorZona(@PathVariable("zona") Zona zona, @PageableDefault (size = 40) Pageable page){

        if (zona != null){
            return ResponseEntity.ok(nodoRepository.findByZona(zona));
        }

        return ResponseEntity.notFound().build();
    }
}
