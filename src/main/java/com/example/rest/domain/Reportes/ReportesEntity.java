package com.example.rest.domain.Reportes;

import com.example.rest.domain.InfoRepo.InfoRepoEntity;
import com.example.rest.domain.Nodos.NodoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nodo_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private NodoEntity nodoEntity;

    @OneToMany(mappedBy = "reportesEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InfoRepoEntity> inforepo = new ArrayList<>();

    public ReportesEntity(ReportesDtoP reportesDtoP){
        this.fecha = reportesDtoP.fecha();
        this.categoriaact = reportesDtoP.categoriaAct();
        this.motivo_visita = reportesDtoP.motivo_visita();
        this.actividad_realizadas = reportesDtoP.actividad_realizada();
        this.observaciones = reportesDtoP.observaciones();
        this.hora_entrada = reportesDtoP.hora_entrada();
        this.hora_salida = reportesDtoP.hora_salida();
        this.personal_encargado = reportesDtoP.personal_encargado();
        this.nodoEntity = reportesDtoP.nodo_id();
    }

    //ojo categoriaact
    public ReportesEntity(Date fecha, CategoriaAct categoriaact, String motivo_visita, String actividad_realizadas, String observaciones, LocalTime hora_entrada, LocalTime hora_salida, String personal_encargado, NodoEntity nodo_id ){
        this.fecha = fecha;
        this.categoriaact = categoriaact;
        this.motivo_visita = motivo_visita;
        this.actividad_realizadas = actividad_realizadas;
        this.observaciones = observaciones;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.personal_encargado = personal_encargado;
        this.nodoEntity = nodo_id;
    }

    public void actualizarReporte(ReportesDtoPut reportesDtoPut){
        this.fecha = reportesDtoPut.fecha();
        this.categoriaact = reportesDtoPut.categoria_actividad();
        this.motivo_visita = reportesDtoPut.motivo_visita();
        this.actividad_realizadas = reportesDtoPut.actividad_realizada();
        this.observaciones = reportesDtoPut.observaciones();
        this.hora_entrada = reportesDtoPut.hora_entrada();
        this.hora_salida = reportesDtoPut.hora_salida();
        this.personal_encargado = reportesDtoPut.personal_encargado();
        this.nodoEntity = reportesDtoPut.nodo_id();
    }
}
