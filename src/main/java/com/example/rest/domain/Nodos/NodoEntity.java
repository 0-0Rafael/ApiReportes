package com.example.rest.domain.Nodos;

import com.example.rest.domain.Reportes.ReportesEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Array;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nodos")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "nodo_id")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class NodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nodo_id;

    @Enumerated(EnumType.STRING)
    private Nodo nodo;

    @Enumerated(EnumType.STRING)
    private Fase fase;

    @Enumerated(EnumType.STRING)
    private Zona zona;

    @OneToMany(mappedBy = "nodoEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportesEntity> reportes = new ArrayList<>();

    public NodoEntity(NodoDtoP nodoDtoP){
        this.nodo = nodoDtoP.nodo();
        this.fase = nodoDtoP.fase();
        this.zona = nodoDtoP.zona();
    }

    public void actualizarNodo(NodoDtoPut nodoDtoPut){
        this.nodo = nodoDtoPut.nodo();
        this.fase = nodoDtoPut.fase();
        this.zona = nodoDtoPut.zona();
    }
}
