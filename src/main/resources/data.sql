INSERT INTO USUARIO(id, nome, email, senha) VALUES(1,'Aluno', 'aluno@email.com', '123456');

INSERT INTO CURSO(id, nome, categoria) VALUES(1,'Spring Boot', 'Programação');
INSERT INTO CURSO(id, nome, categoria) VALUES(2,'HTML 5', 'Front-end');

INSERT INTO TOPICO(id, titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES(1,'Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(id, titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES(2,'Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(id, titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES(3,'Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);