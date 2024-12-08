-- INSERIR CATEGORIAS --
INSERT INTO tb_categoria (id, nome) VALUES (1, 'Rasteira')
INSERT INTO tb_categoria (id, nome) VALUES (2, 'Tênis Masculino')
INSERT INTO tb_categoria (id, nome) VALUES (3, 'Tênis Feminino')
INSERT INTO tb_categoria (id, nome) VALUES (4, 'Bolsa')
INSERT INTO tb_categoria (id, nome) VALUES (5, 'Sandália')

-- INSERIR PRODUTOS --
INSERT INTO tb_produto (id, nome, descricao, preco, unidades_em_estoque, imagem_principal, data_criacao, ultima_atualizacao, ativo) VALUES (1, 'Rasteira', 'Uma Rasteirinha confortável e super elegante', 150.99, 50, 'images/collections/rasteirinha_circle.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true)
INSERT INTO tb_produto (id, nome, descricao, preco, unidades_em_estoque, imagem_principal, data_criacao, ultima_atualizacao, ativo) VALUES (2, 'Tênis Masculino', 'Um tênis aconchegante e confortável', 199.90, 25, 'images/collections/tenis_masculino_circle.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true)
INSERT INTO tb_produto (id, nome, descricao, preco, unidades_em_estoque, imagem_principal, data_criacao, ultima_atualizacao, ativo) VALUES (3, 'Tênis Feminino', 'Um tênis aconchegante e confortável', 120.00, 35, 'images/collections/tenis_feminino_circle.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true)
INSERT INTO tb_produto (id, nome, descricao, preco, unidades_em_estoque, imagem_principal, data_criacao, ultima_atualizacao, ativo) VALUES (4, 'Bolsa', 'Uma bolsa feita à mão', 170.00, 21, 'images/collections/bolsas_circle.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false)
INSERT INTO tb_produto (id, nome, descricao, preco, unidades_em_estoque, imagem_principal, data_criacao, ultima_atualizacao, ativo) VALUES (5, 'Sandália', 'Uma sandália confortável', 110.00, 22, 'images/collections/sandalia_circle.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true)

-- RELACIONAR PRODUTOS E CATEGORIAS --
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (1, 1)
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (2, 2)
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (3, 3)
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (4, 4)
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (5, 5)
