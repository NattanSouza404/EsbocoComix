INSERT INTO bandeiras_cartao_credito (bcc_nome) VALUES ('MASTERCARD');
INSERT INTO bandeiras_cartao_credito (bcc_nome) VALUES ('VISA');
INSERT INTO bandeiras_cartao_credito (bcc_nome) VALUES ('AMERICAN_EXPRESS');
INSERT INTO bandeiras_cartao_credito (bcc_nome) VALUES ('HIPERCARD');
INSERT INTO bandeiras_cartao_credito (bcc_nome) VALUES ('ELO');

INSERT INTO grupos_precificacao (gpr_nome, gpr_porcentagem) VALUES
  ('Colecionador', 60.00),
  ('Premium',     50.00),
  ('Padrão',      30.00),
  ('Promoção',    15.00);

INSERT INTO categorias (cat_nome) VALUES
   ('Fantasia Épica'),
   ('Aventura'),
   ('Quadrinho Nacional Adulto'),
   ('Ação'),
   ('Infantojuvenil'),
   ('Adaptação de Jogo'),
   ('Humor'),
   ('Quadrinho Independente'),
   ('Infantil'),
   ('Clássico Brasileiro'),
   ('Aventura Policial'),
   ('Protagonismo Feminino'),
   ('Mistério'),
   ('Clássico Europeu'),
   ('Adaptação Literária'),
   ('Fábula Filosófica');

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Franco de Rosa, Mozart Couto', '2000-01-01', 'Conancraft', 'Tabula Editora', 1, '1112223334445', 20,
	
	'O bárbaro on the blocks
	Essa é uma ideia fantástica e divertida! Parodiar Conan usando o visual icônico de blocos do Minecraft é uma maneira inventiva de homenagear 
	o clássico personagem e seu universo, rompendo com os cânones tradicionais do gênero de espada e 
	fantasia. Além disso, brincar com os clichês desse domínio pode render momentos hilários e memoráveis, 
	como o protagonista enfrentando um monstro pixelado ou buscando artefatos no interior de uma torre ou mesmo em baús cheios de tesouros. O importante é manter o respeito ao personagem e ao legado do magistral Robert E. Howard. Uma paródia bem-feita não zomba, mas celebra e reverência. Conan, com sua seriedade e heroísmo, é um contraste perfeito para o estilo despojado e simplificado do mundo de Minecraft, criando uma mistura épica e cômica.
	O visual quadrado do bárbaro cabeludo, carregando uma pesada espada pixelada, conquista o fã na primeira olhada. A capa é uma atração à parte e traz uma ilustração que imita uma famosa imagem criada pelo exímio artista de Conan Earl Norem.
	Se o tom da narrativa é épico, a arte épica ao cubo (com perdão do trocadilho infame). Imagine uma aventura em que Conan enfrenta criaturas creepers, feiticeiros e mercenários com muito sangue de nanquim jorrando aos borbotões!
	Tá aqui. Uma HQ que tem tudo para ser uma experiência imponente no melhor sentido da palavra!',

	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/conancraft.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Ian Flynn', '2000-01-01', 'Sonic The Hedgehog', 'Geektopia', 1, '1112223334445', 20,
	
	'Dr. Eggman, o gênio do mal, foi vencido – mas o trabalho de Sonic ainda não acabou! No rastro de sua última e épica batalha, bots estão à solta e atacando pelas vilas mundo afora. Para derrotá-los, Sonic vai precisar de ajuda dos amigos Tails, Knuckles e Amy, assim como de novos e incríveis aliados. Correndo velozmente das telas dos games para as páginas dos quadrinhos, o ouriço azul tão querido por todos nós embala essa nova série trazida com exclusividade pela Geektopia, com tradução do grande Érico Assis. Um quadrinho com a excelência de sempre, feito com carinho para todas as gerações de leitores',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/sonic.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (2, 4),
  (2, 5),
  (2, 6);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Ede Galileu, Edde Wagner e Al Stefano e CMK', '2000-01-01', 'As Aventuras do Homem Chiclete', 'Independente', 1, '1112223334445', 20,
	
	'Você sabe qual o charme do Homem-Chiclete?
	Em suas histórias, o super-herói de Jundiaí (cidade no interior de SP) possui as propriedades de um chiclete sabor tutti-frutti, que pode se esticar e se contorcer praticamente de qualquer forma. Mas, apesar desses poderes, sua maior qualidade é o otimismo e o bom humor com que reage às adversidades. 
	Nessa edição, o destaque é o inusitado carro do nosso herói. Mas o livro ainda conta com outras aventuras e muitas piadinhas para divertir e estimular a leitura na criançada.',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/homemchiclete.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (3, 7),
  (3, 2),
  (3, 8);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Ziraldo', '2000-01-01', 'O Menino Maluquinho', 'Melhoramentos', 1, '1112223334445', 20,
	
	'Aquele era um menino muito sabido, esperto, inteligente! Tinha macaquinhos no sótão, embora não soubesse o que isso queria dizer... Brincava, agitava a casa, animava a todos com sua energia e vivacidade. Seria ele um anjinho, um saci? Alegria da casa, na escola, liderava a garotada na hora do intervalo. Adorava fazer versinhos, compor canções, inventar novos jogos e brincadeiras. Era um amigão. “Que Menino Maluquinho”, diziam sorrindo as pessoas que o conheciam. Não era nada disso, não! Só mais tarde descobriram que ele tinha sido um garoto muito amado e muito feliz.',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/meninomaluquinho.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (4, 9),
  (4, 10),
  (4, 7);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Dav Pikey', '2000-01-01', 'O Homem-Cão', 'Companhia das Letrinhas', 1, '1112223334445', 20,
	
	'Antes do Capitão Cueca, Jorge e Haroldo criaram um novo herói que bebe água da privada, rola sobre os bandidos e late na cara do perigo! Quando o oficial Rocha e seu cachorro Greg sofrem um acidente, o único jeito de os dois sobreviverem é fundindo a cabeça do cão com o corpo do policial — e é assim que nasce o Homem-Cão, o melhor policial da cidade! Porém, ele tem um grande inimigo: o terrível gato Pepê, e não vai ser nada fácil enfrentar esse vilão que até pode parecer fofinho, mas tem milhões de ideias maldosas na cabeça.',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/homemcao.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (5, 9),
  (5, 7),
  (5, 11);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Ziraldo', '2000-01-01', 'Aventuras da Julieta', 'Globinho', 1, '1112223334445', 20,
	
	'O livro reúne histórias de Julieta, personagem da turma do Menino Maluquinho criada por Ziraldo. A coletânea traz algumas das aventuras mais incríveis dessa menina pra lá de maluquinha. Ela é a líder da turma, que está sempre bolando novas brincadeiras e inventando modas muito bem inventadas: lutar judô, explorar lugares desconhecidos e torcer com muita animação. Aventurese com a menina da camiseta de raio e seus amigos.',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/aventurasdajulieta.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (6, 9),
  (6, 2),
  (6, 12);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Hergé', '2000-01-01', 'O tesouro de Rackham, O Terrível', 'Quadrinhos na CIA', 1, '1112223334445', 20,
	
	'Em O tesouro de Rackham, o Terrível, seqüência de O segredo do Licorne, Tintim e o capitão Haddock, já de posse das três partes do mapa com a localização do naufrágio da embarcação de Rackham, partem em busca dos destroços do Licorne. Junta-se a eles o extravagante professor Girassol, que se tornará um companheiro constante dos nossos heróis. Um invento do professor ajudará muito nas buscas: um mini-submarino com formato de tubarão. Depois de desembarcarem numa ilha deserta, onde encontram vários indícios da passagem do cavaleiro de Hadoque, finalmente descobrem o Licorne no fundo do mar. No navio, acham um pequeno cofre. O que haveria ali dentro? Será que o tesouro se limitava somente ao que continha? É o que você vai saber lendo esta aventura.',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/tintin.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (7, 2),
  (7, 13),
  (7, 14);

INSERT INTO quadrinhos(
	qua_preco, qua_autor, qua_ano, qua_titulo, qua_editora, qua_edicao, qua_isbn, qua_numero_paginas, qua_sinopse, qua_altura_cm, qua_largura_cm, qua_profundidade, qua_peso_gramas, qua_codigo_barras, qua_is_ativo, qua_gpr_id, qua_url_imagem)
	VALUES (30, 'Mauricio de Sousa', '2000-01-01', 'Turma da Mônica - o Pequeno Príncipe', 'Girassol', 1, '1112223334445', 20,
	
	'Livro de criança; livro para adulto também. Quem não traz em si o menino que já foi? O Pequeno Príncipe devolve o mistério da infância e faz renascer os sonhos. O maior clássico da literatura infantil mundial lindamente adaptado e ilustrado com os personagens de Mauricio de Sousa.',
	
	10, 10, 2, 200, '1111111111111', true, 1, '/img/quadrinhos/turmadamonica.jpg');

INSERT INTO categorias_quadrinho (cqu_qua_id, cqu_cat_id) VALUES
  (8, 9),
  (8, 15),
  (8, 16);

UPDATE quadrinhos
  SET qua_gpr_id = 1
  WHERE qua_titulo = 'Conancraft';

UPDATE quadrinhos
  SET qua_gpr_id = 3
  WHERE qua_titulo = 'Sonic The Hedgehog';

UPDATE quadrinhos
  SET qua_gpr_id = 4
  WHERE qua_titulo = 'As Aventuras do Homem Chiclete';

UPDATE quadrinhos
  SET qua_gpr_id = 3
  WHERE qua_titulo = 'O Menino Maluquinho';

UPDATE quadrinhos
  SET qua_gpr_id = 4
  WHERE qua_titulo = 'O Homem-Cão';

UPDATE quadrinhos
  SET qua_gpr_id = 3
  WHERE qua_titulo = 'Aventuras da Julieta';

UPDATE quadrinhos
  SET qua_gpr_id = 2
  WHERE qua_titulo = 'O tesouro de Rackham, O Terrível';

UPDATE quadrinhos
  SET qua_gpr_id = 2
  WHERE qua_titulo = 'Turma da Mônica - o Pequeno Príncipe';

-- A ajustar ainda
INSERT INTO clientes VALUES ('Jorge dos Santos Menezes', 'MASCULINO', '1998-12-20', '11122233344', 'jorge@email.com', '696YDOwfrZKwB8Zc053W7AIU7IPVPEEpTBDaz2edWk8=', 'YIGCwc/BvOPLriML2n0zng==', 0, 'FIXO', '11', '99999999 ', true);