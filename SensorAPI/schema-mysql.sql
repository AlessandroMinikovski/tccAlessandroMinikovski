CREATE TABLE IF NOT EXISTS bootdb.sensor (
    id int NOT NULL AUTO_INCREMENT,
    modelo varchar(255) NOT NULL,
    finalidade varchar(255) NOT NULL,
    tipoConexao varchar(255) NOT NULL,
    valor double,
    PRIMARY KEY (id)
);