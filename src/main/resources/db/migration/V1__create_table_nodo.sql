CREATE TABLE nodos (
           nodo_id bigint not null auto_increment,
           nodo varchar(255) not null,
           fase varchar(255) not null,
           zona varchar(255),
           primary key (nodo_id)
);