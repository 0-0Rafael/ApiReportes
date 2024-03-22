package com.application.rest.domain.InfoRepo;

import com.application.rest.domain.Reportes.ReportesEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InfoRepoDtoP(

        @NotBlank
        Long id,
        @NotBlank
        String foto,
        @NotNull
        ReportesEntity reporte_id


) {

    public InfoRepoEntity toEntity() {
        return new InfoRepoEntity(id,foto,reporte_id);
    }

}
