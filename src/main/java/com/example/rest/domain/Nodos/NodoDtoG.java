package com.example.rest.domain.Nodos;


/*
Este record sirve como un puente entre las entidades y los clientes para mandar y obtener datos.
*/

public record NodoDtoG(
        Long id,
        Nodo nodo,
        Fase fase,
        Zona zona

) {
    public NodoDtoG(NodoEntity nodo){
        this(nodo.getNodo_id(), nodo.getNodo(), nodo.getFase(), nodo.getZona());
    }
}
