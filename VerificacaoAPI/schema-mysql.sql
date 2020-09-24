CREATE TABLE IF NOT EXISTS bootdb.verificacao (
    id int NOT NULL AUTO_INCREMENT,
    idCondi int NOT NULL,
    idAlerta int NOT NULL,
    idSensor int NOT NULL,
    frequencia int NOT NULL,
    acionado varchar(1) NOT NULL,
    PRIMARY KEY (id)
);