package com.application.rest.domain.Reportes;

import com.application.rest.domain.InfoRepo.InfoRepoEntity;
import com.application.rest.domain.Nodos.NodoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reportes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "reporte_id")
public class ReportesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reporte_id;
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private CategoriaAct categoriaact;

    private String motivo_visita;
    private String actividad_realizadas;
    private String observaciones;

    private LocalTime hora_entrada;
    private LocalTime hora_salida;

    private String personal_encargado;

    //private Nodo_Entity nodoEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nodo_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private NodoEntity nodoEntity;

    @OneToMany(mappedBy = "reportesEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InfoRepoEntity> inforepo = new ArrayList<>();


}
