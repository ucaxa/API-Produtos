CREATE TABLE produtos
(
    id                      bigint(20) NOT NULL AUTO_INCREMENT,
    nome                    varchar(255)   NOT NULL,
    descricao                    varchar(255)   NOT NULL,
    preco                   decimal(38, 2) NOT NULL,
    quantidade_estoque int NOT NULL,
    PRIMARY KEY (id)
)