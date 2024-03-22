package com.application.rest.domain.InfoRepo;

import com.application.rest.domain.Reportes.ReportesEntity;

import java.util.Date;

public record InfoRepoDtoG(
        Long inforepo_id,
        String foto,
        ReportesEntity reporte_id

) {

    public InfoRepoDtoG(InfoRepoEntity infoRepo){
        this(infoRepo.getInfo_id(), infoRepo.getFoto(), infoRepo.getReportesEntity());
    }

}
