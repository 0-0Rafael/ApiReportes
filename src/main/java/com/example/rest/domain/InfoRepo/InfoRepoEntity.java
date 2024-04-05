package com.example.rest.domain.InfoRepo;

import com.example.rest.domain.Reportes.ReportesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

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
    public InfoRepoEntity(InfoRepoDtoP infoRepoDtoP){
        this.foto = infoRepoDtoP.foto();
        this.reportesEntity = infoRepoDtoP.reporte_id();
    }
    public InfoRepoEntity(String foto, ReportesEntity reporte_id){
        this.foto = foto;
        this.reportesEntity = reporte_id;
    }
    public void actualizarInfoRepo(InfoRepoDtoPut infoRepoDtoPut){
        this.foto = infoRepoDtoPut.foto();
        this.reportesEntity = infoRepoDtoPut.reporte_id();
    }
    public ReportesEntity getReportesEntity() {
        if (reportesEntity instanceof HibernateProxy) {
            Hibernate.initialize(reportesEntity);
            reportesEntity = (ReportesEntity) ((HibernateProxy) reportesEntity).getHibernateLazyInitializer().getImplementation();
        }
        return reportesEntity;
    }

}

