package com.example.rest.domain.Nodos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodoRepository extends JpaRepository<NodoEntity, Long> {
    NodoEntity findByNodo (Nodo nodo);
    List<NodoEntity> findByFase (Fase fase);
    List<NodoEntity> findByZona(Zona zona);

}
