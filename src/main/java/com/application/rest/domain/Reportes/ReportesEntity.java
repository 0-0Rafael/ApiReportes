package com.application.rest.domain.Reportes;

import com.application.rest.domain.Nodos.Fase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "reportes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "reporte_id")
public class ReportesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reporte_id;
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private Categoria_Actividad categoria_actividad;

    private String motivo_visita;
    private String actividad_realizadas;
    private String observaciones;

    private LocalTime hora_entrada;
    private LocalTime hora_salida;

    private String personal_encargado;



}
