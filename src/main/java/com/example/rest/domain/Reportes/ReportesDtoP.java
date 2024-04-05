package com.example.rest.domain.Reportes;

import com.example.rest.domain.InfoRepo.InfoRepoEntity;
import com.example.rest.domain.Nodos.NodoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record ReportesDtoP(

        @NotNull
        @JsonFormat(pattern = "yyyy/MM/dd")
        Date fecha,
        @NotNull
        CategoriaAct categoriaAct,
        @NotBlank
        String motivo_visita,
        @NotBlank
        String actividad_realizada,
        @NotBlank
        String observaciones,
        @NotNull
        @JsonFormat(pattern = "HH:mm")
        LocalTime hora_entrada,
        @NotNull
        @JsonFormat(pattern = "HH:mm")
        LocalTime hora_salida,
        @NotBlank
        String personal_encargado,

        @NotNull
        NodoEntity nodo_id

) {

}
