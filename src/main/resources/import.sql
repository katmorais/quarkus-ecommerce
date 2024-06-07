insert into estado (nome, sigla) values( 'Tocantins', 'TO');
insert into estado (nome, sigla) values( 'Goiás', 'GO');
insert into estado (nome, sigla) values( 'São Paulo', 'SP');
insert into estado (nome, sigla) values( 'Rio de Janeiro', 'RJ');
insert into estado (nome, sigla) values( 'Pará', 'PA');

insert into cidade (nome, id_estado) values( 'Palmas', 1);
insert into cidade (nome, id_estado) values( 'Paraíso', 1);
insert into cidade (nome, id_estado) values( 'Gurupi', 1);
insert into cidade (nome, id_estado) values( 'Goiânia', 2);
insert into cidade (nome, id_estado) values( 'Anápolis', 2);

INSERT INTO fornecedor (nome, dataContrato) VALUES ('SuaModa', '2025-05-10');
INSERT INTO fornecedor (nome, dataContrato) VALUES ('TechSuprimentos', '2024-11-20');
INSERT INTO fornecedor (nome, dataContrato) VALUES ('VerdeJardim', '2027-07-15');
INSERT INTO fornecedor (nome, dataContrato) VALUES ('EletroMais', '2025-03-01');
INSERT INTO fornecedor (nome, dataContrato) VALUES ('FashionStyle', '2028-09-05');

INSERT INTO tipoCamiseta (nome) VALUES ('Camiseta Casual');
INSERT INTO tipoCamiseta (nome) VALUES ('Camiseta Esportiva');
INSERT INTO tipoCamiseta (nome) VALUES ('Regata');
INSERT INTO tipoCamiseta (nome) VALUES ('Polo');
INSERT INTO tipoCamiseta (nome) VALUES ('Manga Longa');

INSERT INTO marca (nome, descricao) VALUES ('Nike', 'Marca conhecida por seus produtos esportivos de alta qualidade.');
INSERT INTO marca (nome, descricao) VALUES ('Adidas', 'Marca global de artigos esportivos e moda.');
INSERT INTO marca (nome, descricao) VALUES ('Puma', 'Empresa alemã especializada em calçados esportivos.');
INSERT INTO marca (nome, descricao) VALUES ('Gucci', 'Marca de luxo italiana, reconhecida por seus produtos de moda.');
INSERT INTO marca (nome, descricao) VALUES ('Zara', 'Marca de moda internacional, com foco em roupas acessíveis e modernas.');

-- senha 111
insert into usuario (username, senha) values ('janio', '1BTSU7LatsY6BK9FYiXT9du++eWLisRMBwGyxbRSh0ZpH0p3jIIAWh1+Ta/zBagkeJa/NwPo5XngKb/REUH74g==');
insert into usuario (username, senha) values ('leandra', '222');
insert into usuario (username, senha) values ('carlos', '333');

insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('111.111.111-11', 'Janio Teixeira', '1980-08-26', 1);
insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('222.222.222-22', 'Leandra', '1985-08-12', 2);
insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('333.333.333-33', 'Carlos Henrique', '1982-06-11', 3);
insert into pessoa (cpf, nome, datanascimento, id_usuario) values ('444.444.444-44', 'Fredson', '1982-06-11', null);

insert into cliente (id_pessoa, id_cidade) values (1, 4);
insert into cliente (id_pessoa, id_cidade) values (2, 1);
insert into cliente (id_pessoa, id_cidade) values (4, 2);

insert into funcionario(id_pessoa) values (3);

INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES('Camiseta Azul', 'Camiseta básica azul', 50, 29.99, 'Estampa floral', 'Algodão', 1, 1, 1, 'azul_floral.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES('Camiseta Preta', 'Camiseta casual preta', 20, 39.99, 'Estampa abstrata', 'Poliéster', 2, 2, 2, 'preta_abstrata.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES('Camiseta Branca', 'Camiseta branca clássica', 100, 19.99, 'Sem estampa', 'Algodão', 1, 1, 3, 'branca_classica.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES('Camiseta Vermelha', 'Camiseta vermelha vibrante', 15, 49.99, 'Estampa de logo', 'Algodão', 3, 3, 1, 'vermelha_logo.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES ('Camiseta Amarela', 'Camiseta casual amarela', 35, 44.99, 'Estampa de listras', 'Algodão', 2, 2, 2, 'amarela_listras.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES ('Camiseta Verde', 'Camiseta verde limão', 10, 29.99, 'Estampa de palmeiras', 'Algodão', 3, 1, 1, 'verde_palmeiras.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES('Camiseta Cinza', 'Camiseta cinza mescla', 25, 34.99, 'Sem estampa', 'Poliéster', 1, 3, 3, 'cinza_mescla.jpg');
INSERT INTO camiseta (nome, descricao, estoque, preco, estampa, tecido, id_fornecedor, id_tipoCamiseta, id_marca, nomeImagem) VALUES('Camiseta Laranja', 'Camiseta laranja vibrante', 20, 39.99, 'Estampa de abacaxi', 'Algodão', 2, 2, 1, 'laranja_abacaxi.jpg');
