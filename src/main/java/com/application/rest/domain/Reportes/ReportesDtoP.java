package com.application.rest.domain.Reportes;

import com.application.rest.domain.InfoRepo.InfoRepoEntity;
import com.application.rest.domain.Nodos.NodoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record ReportesDtoP(

        @NotBlank
        Long id,
        @NotBlank
        Date fecha,
        @NotNull
        CategoriaAct categoria_actividad,
        @NotBlank
        String motivo_visita,
        @NotBlank
        String actividad_realizada,
        @NotBlank
        String observaciones,
        @NotBlank
        LocalTime hora_entrada,
        @NotBlank
        LocalTime hora_salida,
        @NotBlank
        String personal_encargado,

        @NotNull
        NodoEntity nodo_id,

        @NotNull
        List<InfoRepoEntity> inforepo

) {

        public ReportesEntity toEntity (){
                return new ReportesEntity(id, fecha, categoria_actividad, motivo_visita, actividad_realizada, observaciones, hora_entrada, hora_salida, personal_encargado, nodo_id, inforepo);
        }

}
