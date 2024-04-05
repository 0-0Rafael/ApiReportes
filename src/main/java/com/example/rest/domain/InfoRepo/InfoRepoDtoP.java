package com.example.rest.domain.InfoRepo;

import com.example.rest.domain.Reportes.ReportesEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InfoRepoDtoP(


        @NotBlank
        String foto,
        @NotNull
        ReportesEntity reporte_id


) {

}
