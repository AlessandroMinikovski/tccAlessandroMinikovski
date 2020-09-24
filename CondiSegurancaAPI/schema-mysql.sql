CREATE TABLE IF NOT EXISTS bootdb.condiSeguranca (
    id int NOT NULL AUTO_INCREMENT,
    nomeCondi varchar(255) NOT NULL,
    idSensor int NOT NULL,
    valorMaximoSensor double ,
    valorMinimoSensor double ,
    tipoAcionamento int NOT NULL,
    PRIMARY KEY (id)
);