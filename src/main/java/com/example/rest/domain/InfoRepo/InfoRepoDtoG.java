package com.example.rest.domain.InfoRepo;

import com.example.rest.domain.Reportes.ReportesEntity;

import java.util.Date;

public record InfoRepoDtoG(
        Long info_id,
        String foto,
        ReportesEntity reporte_id

) {

    public InfoRepoDtoG(InfoRepoEntity infoRepo){
        this(infoRepo.getInfo_id(), infoRepo.getFoto(), infoRepo.getReportesEntity());
    }

}

