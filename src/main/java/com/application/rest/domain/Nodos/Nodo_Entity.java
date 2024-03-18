package com.application.rest.domain.Nodos;

import com.application.rest.domain.Nodos.Fase;
import com.application.rest.domain.Nodos.Nodo;
import com.application.rest.domain.Nodos.Zona_Nodo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "nodo_id")
public class Nodo_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Nodo nodo;

    @Enumerated(EnumType.STRING)
    private Fase fase;

    @Enumerated(EnumType.STRING)
    private Zona_Nodo zona_nodo;
}
