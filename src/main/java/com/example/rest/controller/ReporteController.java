package com.example.rest.controller;


import com.example.rest.domain.Nodos.NodoEntity;
import com.example.rest.domain.Nodos.NodoRepository;
import com.example.rest.domain.Reportes.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.text.ParseException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReportesRepository reportesRepository;

    @Autowired
    private NodoRepository nodoRepository;


    @PostMapping
    public ResponseEntity<ReportesDtoG> guardarReporte(@RequestBody @Valid ReportesDtoP reportesDtoP, UriComponentsBuilder builder){
        System.out.println("El post fue correcto (Se a guardado correctamente el reporte) ");

        ReportesEntity reportes = reportesRepository.save(new ReportesEntity(reportesDtoP));

        ReportesDtoG reportesDtoG = new ReportesDtoG(reportes.getReporte_id(), reportes.getFecha(),
                reportes.getCategoriaact(), reportes.getMotivo_visita(),
                reportes.getActividad_realizadas(), reportes.getObservaciones(),
                reportes.getHora_entrada(), reportes.getHora_salida(),
                reportes.getPersonal_encargado(), reportes.getNodoEntity());

        URI url = builder.path("/reportes/{id}").buildAndExpand(reportes.getReporte_id()).toUri();

        return ResponseEntity.created(url).body(reportesDtoG);

    }

    @GetMapping
    public ResponseEntity<Page<ReportesDtoG>> listadoReporte (@PageableDefault (size = 40)Pageable pageable){
        return ResponseEntity.ok(reportesRepository.findAll(pageable).map(ReportesDtoG::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarReporte(@RequestBody @Valid ReportesDtoPut actualizarReporte){

        ReportesEntity reportes = reportesRepository.getReferenceById(actualizarReporte.id());
        reportes.actualizarReporte(actualizarReporte);

        return ResponseEntity.ok(new ReportesDtoG(reportes.getReporte_id(), reportes.getFecha(), reportes.getCategoriaact(), reportes.getMotivo_visita(), reportes.getActividad_realizadas(), reportes.getObservaciones(), reportes.getHora_entrada(), reportes.getHora_salida(), reportes.getPersonal_encargado(), reportes.getNodoEntity()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarReporte(@PathVariable Long id){
        ReportesEntity reportes = reportesRepository.getReferenceById(id);

        reportesRepository.delete(reportes);
        System.out.println("Reporte eliminado correctamente");

        return ResponseEntity.noContent().build();

    }

    @GetMapping(path = "categoria/{categoria}")
    public ResponseEntity obtenerReporteporCategoria(@PathVariable("categoria") CategoriaAct categoriaAct, @PageableDefault(size = 40) Pageable page){

        if (categoriaAct != null){
            return ResponseEntity.ok(reportesRepository.findByCategoriaact(categoriaAct));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "fecha/{fecha}")
    public ResponseEntity obtenerReporteporFecha1(@PathVariable("fecha") String fechaString, @PageableDefault(size = 40) Pageable page) {

        if (fechaString != null && !fechaString.isEmpty()) {
            // Intenta convertir la cadena de fecha al formato deseado
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fecha = formatter.parse(fechaString);
                // Realiza la consulta utilizando la fecha convertida
                return ResponseEntity.ok(reportesRepository.findByFecha(fecha));
            } catch (ParseException e) {
                // Si hay un error al convertir la fecha, devuelve una respuesta de error
                return ResponseEntity.badRequest().body("Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
            }
        }
        // Si la fecha es nula o vacía, devuelve una respuesta de no encontrado
        return ResponseEntity.notFound().build();
    }


    @GetMapping(path = "fecharango")
    public ResponseEntity<Page<ReportesDtoG>> obtenerReportesPorRangoFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin,
            @PageableDefault(size = 40) Pageable pageable) {

        if (fechaInicio.after(fechaFin)) {
            return ResponseEntity.badRequest().body(Page.empty());

        }
        // Realiza la consulta utilizando el rango de fechas
        Page<ReportesEntity> reportes = reportesRepository.findByFechaBetween(fechaInicio, fechaFin, pageable);
        // Convierte el resultado a DTO y devuelve la respuesta
        return ResponseEntity.ok(reportes.map(ReportesDtoG::new));
    }
}

