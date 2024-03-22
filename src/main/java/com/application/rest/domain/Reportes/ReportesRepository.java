package com.application.rest.domain.Reportes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportesRepository extends JpaRepository<ReportesEntity, Long> {

    List<ReportesEntity> findByFecha (Date fecha);

    //No debe de tener _ ejemplo Categoria_Actividad dara error
    List<ReportesEntity> findByCategoriaact (CategoriaAct categoriaAct);

}
