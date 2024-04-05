package com.example.rest.domain.Reportes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportesRepository extends JpaRepository<ReportesEntity, Long> {

    List<ReportesEntity> findByFecha (Date fecha);
    Page<ReportesEntity> findByFechaBetween(Date fechaInicio, Date fechaFin, Pageable pageable);

    //No debe de tener _ ejemplo Categoria_Actividad dara error
    List<ReportesEntity> findByCategoriaact (CategoriaAct categoriaAct);

}

