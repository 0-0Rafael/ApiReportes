package com.example.rest.domain.Reportes;

import com.example.rest.domain.InfoRepo.InfoRepoEntity;
import com.example.rest.domain.Nodos.NodoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public record ReportesDtoPut(

        @NotBlank
        Long id,
        @NotBlank
        @JsonFormat(pattern = "yyyy-MM-dd")
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
        @JsonFormat(pattern = "HH:mm")
        LocalTime hora_entrada,
        @NotBlank
        @JsonFormat(pattern = "HH:mm")
        LocalTime hora_salida,
        @NotBlank
        String personal_encargado,

        @NotNull
        NodoEntity nodo_id
) {
}
