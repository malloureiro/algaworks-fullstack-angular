CREATE TABLE pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(80),
	numero VARCHAR(10),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(10),
	cidade VARCHAR(30),
	estado VARCHAR(30)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES ('José Roberto Santos', true, 'Rua Monsenhor João Soares', '100', '', 'Centro', '17625902', 'Sorocaba', 'São Paulo');

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES ('Maria Aparecida Santana', true, 'Av Pereira da Silva', 'B76', 'Fundos', 'Santa Rosalia', '18010-010', 'Sorocaba', 'São Paulo');

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES ('Carlos Alberto de Souza', true, 'R Pagliato Neto', '1123', '', 'Centro', '19090-010', 'Sorocaba', 'São Paulo');