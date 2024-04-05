CREATE TABLE reportes (
          reporte_id bigint not null auto_increment,
          fecha DATE not null,
          categoria_actividad VARCHAR(255) not null,
          motivo_visita VARCHAR(255) not null,
          actividad_realizadas VARCHAR(5000) not null,
          observaciones VARCHAR(5000),
          hora_entrada TIME not null,
          hora_salida TIME not null,
          personal_encargado VARCHAR (5000),
          nodo_id bigint,

          PRIMARY KEY (reporte_id)

);