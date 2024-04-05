package com.example.rest.domain.InfoRepo;

import com.example.rest.domain.Reportes.ReportesEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InfoRepoDtoPut(

        @NotNull
        Long info_id,
        @NotBlank
        String foto,
        @NotNull
        ReportesEntity reporte_id

) {
}
