package com.example.rest.domain.Reportes;

import com.example.rest.domain.Nodos.NodoEntity;

import java.time.LocalTime;
import java.util.Date;

public record ReportesDtoG(
        Long reporte_id,
        Date fecha,
        CategoriaAct categoria_actividad,
        String motivo_visita,
        String actividad_realizada,
        String observaciones,
        LocalTime hora_entrada,
        LocalTime hora_salida,
        String personal_encargado,

        NodoEntity nodo_id


) {
    public ReportesDtoG(ReportesEntity reportes){
        this(reportes.getReporte_id(), reportes.getFecha(), reportes.getCategoriaact(), reportes.getMotivo_visita(),
                reportes.getActividad_realizadas(), reportes.getObservaciones(), reportes.getHora_entrada(),
                reportes.getHora_salida(), reportes.getPersonal_encargado(), reportes.getNodoEntity());
    }

}
