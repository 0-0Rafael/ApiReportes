package com.application.rest.domain.InfoRepo;


import com.application.rest.domain.Nodos.NodoEntity;
import com.application.rest.domain.Reportes.ReportesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "inforepo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "info_id")

public class InfoRepoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long info_id;

    private String foto;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ReportesEntity reportesEntity;



}
