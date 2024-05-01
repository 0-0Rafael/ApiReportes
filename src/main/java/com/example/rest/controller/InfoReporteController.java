package com.example.rest.controller;

import com.example.rest.domain.InfoRepo.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/inforepo")
@SecurityRequirement(name = "bearer-key")
public class InfoReporteController {

    @Autowired
    private InfoRepoRepository infoRepoRepository;


    @PostMapping
    public ResponseEntity<InfoRepoDtoG> guardarInfoRepo(@RequestBody @Valid InfoRepoDtoP infoRepoDtoP, UriComponentsBuilder builder){
        System.out.println("El post fue correcto (Se a guardado correctamente el reporte) ");

        InfoRepoEntity infoRepo = infoRepoRepository.save(new InfoRepoEntity(infoRepoDtoP));

        InfoRepoDtoG infoRepoDtoG = new InfoRepoDtoG(infoRepo.getInfo_id(), infoRepo.getFoto(),
                infoRepo.getReportesEntity());

        URI url = builder.path("/inforepo/{id}").buildAndExpand(infoRepo.getInfo_id()).toUri();

        return ResponseEntity.created(url).body(infoRepoDtoG);

    }
    @GetMapping
    public ResponseEntity<Page<InfoRepoDtoG>> listarInfoRepo (@PageableDefault(size = 40)Pageable pageable){
        return ResponseEntity.ok(infoRepoRepository.findAll(pageable).map(InfoRepoDtoG::new));
    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarInfoRepo(@RequestBody @Valid InfoRepoDtoPut actualizarInfoRepo){

        InfoRepoEntity infoRepo = infoRepoRepository.getReferenceById(actualizarInfoRepo.info_id());
        infoRepo.actualizarInfoRepo(actualizarInfoRepo);

        return ResponseEntity.ok(new InfoRepoDtoG(infoRepo.getInfo_id(), infoRepo.getFoto(), infoRepo.getReportesEntity()));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarInfoRepo(@PathVariable Long id){
        InfoRepoEntity infoRepo = infoRepoRepository.getReferenceById(id);

        infoRepoRepository.delete(infoRepo);

        return ResponseEntity.noContent().build();
    }
}
