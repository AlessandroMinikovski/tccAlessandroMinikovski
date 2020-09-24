CREATE TABLE IF NOT EXISTS bootdb.alerta (
    id int NOT NULL AUTO_INCREMENT,
    modelo varchar(255) NOT NULL,
    localizacao varchar(255) NOT NULL,
    acionado varchar(1) NOT NULL,
    PRIMARY KEY (id)
);