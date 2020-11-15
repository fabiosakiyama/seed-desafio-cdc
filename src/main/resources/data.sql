INSERT INTO AUTOR (nome, email, descricao, instante_Registrado) VALUES ('Fabio', 'fabio@email.com', 'Autor de nada', '2020-11-11 10:38:04.191954')
INSERT INTO AUTOR (nome, email, descricao, instante_Registrado) VALUES ('Fulano', 'fulano@email.com', 'Autor de Python', '2020-11-11 10:38:04.191954')

INSERT INTO CATEGORIA (nome) VALUES ('Javascript')
INSERT INTO CATEGORIA (nome) VALUES ('Java')
INSERT INTO CATEGORIA (nome) VALUES ('Python')

INSERT INTO LIVRO (titulo, resumo, sumario, preco, numero_de_paginas, isbn, data_de_publicacao, categoria_id, autor_id) VALUES ('Livro de algo', 'Um resumo sobre o livro de algo', null, 25.90, 120, '1-XAS-3', '2020-11-14', 1, 1)
INSERT INTO LIVRO (titulo, resumo, sumario, preco, numero_de_paginas, isbn, data_de_publicacao, categoria_id, autor_id) VALUES ('Livro de Python', 'Um resumo sobre o livro de python', null, 55.92, 170, '1-XAS-4', '2020-11-14', 3, 2)

INSERT INTO PAIS (nome) VALUES ('Brasil')
INSERT INTO PAIS (nome) VALUES ('Argentina')
INSERT INTO PAIS (nome) VALUES ('USA')

INSERT INTO ESTADO (nome, pais_id) VALUES ('São Paulo', 1)
INSERT INTO ESTADO (nome, pais_id) VALUES ('Minas Gerais', 1)
INSERT INTO ESTADO (nome, pais_id) VALUES ('Buenos Aires', 2)