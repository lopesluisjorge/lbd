create database laboratorio05 charset utf8 collate utf8_unicode_ci;

use laboratorio05;

create table cliente (
  id       int unsigned not null auto_increment,
  nome     varchar(64)  not null,
  cpf      varchar(11)  not null,
  endereco longtext     not null,
  telefone varchar(20),
  ativo    smallint,
  primary key (id));

create table filme (
  id             int unsigned not null auto_increment,
  titulo         varchar(255) not null,
  ano_lancamento int          not null,
  duracao        int          not null,
  genero         varchar(255) not null,
  primary key (id));

create table video (
  id           int unsigned not null auto_increment,
  filme_id     int unsigned not null,
  status       smallint,
  tipo         varchar(255),
  valor_diaria decimal(19,2),
  primary key (id),
  foreign key (filme_id) references filme (id));

create table emprestimo (
  id              int unsigned not null auto_increment,
  cliente_id      int unsigned not null,
  data_locacao    date,
  data_devolucao  date,
  valor_aluguel   decimal(19,2),
  status          smallint,
  primary key (id),
  foreign key (cliente_id) references cliente(id));

create table emprestimo_video (
  emprestimo_id int unsigned not null,
  video_id      int unsigned not null,
  primary key (emprestimo_id,video_id),
  foreign key (emprestimo_id) references emprestimo (id),
  foreign key (video_id)      references video (id));



insert into cliente values (1,'Simão Valência Mendes Jr.','12475234385','53693-445, Avenida Espinoza, 66. Fundos\nSimon do Sul - PA','(12) 99096-9259',1),
(2,'Manuela Ariana Espinoza','89343966601','75296-678, Largo Benjamin Montenegro, 78\nSanta Micaela do Leste - RN','(33) 96699-5155',1),
(3,'Dr. Isaac Dante Lovato Neto','59215418695','44957-149, Rua Vale, 638\nSão Fernando do Sul - RR','(41) 94378-5168',1),
(4,'Sebastião Estêvão Salgado Neto','47590726087','09937-927, R. Silvana, 7. Apto 78\nSalgado do Leste - RO','(92) 99915-3339',0),
(5,'Dr. Luciana Ferreira Jr.','04438854350','34073-155, R. Fábio, 34164\nVila Luis do Leste - PI','(85) 97006-2309',1),
(6,'Dr. Bruno Franco Maia Jr.','67449316790','29328-056, Av. Joana, 64085\nPorto Rebeca - CE','(67) 90751-5151',1),
(7,'Dr. Jerônimo Vasques','65158975027','15217-450, Largo Bittencourt, 49. Bloco C\nSão Andrea - PR','(94) 95839-0293',1),
(8,'Abgail Molina','72903862214','72784-462, Avenida Faria, 1. Apto 511\nPorto Olívia do Sul - ES','(91) 90868-6831',1),
(9,'Thalissa Espinoza','73283784507','63218-301, R. Suzana, 89069\nSão Hortência - MA','(86) 95805-5019',0),
(10,'Dr. Alexandre Branco','02313798119','85083-397, R. Suzana Rocha, 091\nSanta Valentin do Norte - MT','(53) 93296-2021',1),
(11,'Thiago Thales Espinoza Sobrinho','12451919779','69843-034, Travessa Estêvão Sandoval, 12\nSanta Pablo - RN','(66) 92227-2932',1),
(12,'Dr. Ornela Molina Jr.','48109314635','59403-553, Avenida Maldonado, 166. Apto 75\nSanta Diego - AC','(31) 93115-0914',0),
(13,'Thiago Rodrigo Pontes','88747519746','81854-823, Av. Carlos, 04770. F\nPaz do Oeste - AC','(62) 91024-0999',1),
(14,'Camila Molina','81431128023','88161-023, Travessa Maitê, 9. Fundos\nSalas do Sul - MT','(99) 93287-3758',1),
(15,'Abgail Rocha Neto','75291518863','34114-639, R. Renata Barreto, 25836\nNeves do Oeste - SE','(84) 98201-1987',1),
(16,'Dr. Noelí Padilha Domingues Neto','64043356706','89565-507, Rua Luna Molina, 3. Apto 3812\nAguiar do Sul - SE','(84) 99723-2217',1),
(17,'Dr. Hugo Escobar Dominato Jr.','48393622115','80400-057, Av. Alan, 53735\nSimão do Oeste - DF','(99) 99405-1798',1),
(18,'Gian Teles','13523487218','09253-765, Travessa Máximo Burgos, 6\nVila Rodrigo do Oeste - AP','(83) 92340-6106',1),
(19,'Sr. Ian Bezerra Jimenes','66265989880','14390-654, Avenida Ivan, 4. Apto 602\nVila Alonso do Oeste - PA','(73) 91881-4468',1),
(20,'Dr. Isadora Ortega','22707053198','87094-281, Av. Daniela Cruz, 034. Bloco A\nHugo do Oeste - PI','(38) 90925-5334',1);



insert into filme values (1,'O Poderoso Chefão',2006,3,'Ação'),
(2,'A Lista de Schindler',1975,3,'Drama'),
(3,'O Rei Leão',2003,1,'Comédia'),
(4,'Um Sonho de Liberdade',2008,3,'Drama'),
(5,'O Senhor dos Anéis - O Retorno do Rei',2010,1,'Drama'),
(6,'O Poderoso Chefão 2',1973,2,'Aventura'),
(7,'A Vida é Bela',1978,3,'Aventura'),
(8,'Forrest Gump - O Contador de Histórias',1973,2,'Ficção Cientifica'),
(9,'À Espera de um Milagre',1970,2,'Comédia'),
(10,'Vingadores: Guerra Infinita',1997,1,'Ficção Cientifica'),
(11,'Batman - O Cavaleiro Das Trevas',2017,2,'Ação'),
(12,'O Império Contra-ataca',1989,2,'Ação'),
(13,'De Volta para o Futuro',1990,2,'Drama'),
(14,'Histórias Cruzadas',2018,3,'Comédia'),
(15,'Harry Potter e as Relíquias da Morte - Parte 2',1978,1,'Ação'),
(16,'O Senhor dos Anéis - A Sociedade do Anel',2017,2,'Aventura'),
(17,'Como Treinar o seu Dragão 3',1976,2,'Aventura'),
(18,'Intocáveis',2004,1,'Ação'),
(19,'O Senhor dos Anéis - As Duas Torres',2003,1,'Aventura'),
(20,'Sempre ao Seu Lado',2006,3,'Comédia'),
(21,'Homens de Honra',2001,1,'Drama'),
(22,'Velozes & Furiosos 7',2016,1,'Ficção Cientifica'),
(23,'Viva - A Vida é uma Festa',2011,1,'Ação'),
(24,'Django Livre',2005,2,'Ação'),
(25,'A Bela e a Fera',2001,2,'Ação'),
(26,'O Resgate do Soldado Ryan',1994,3,'Ação'),
(27,'Até o Último Homem',2012,3,'Ação'),
(28,'Bohemian Rhapsody',1987,2,'Aventura'),
(29,'Orgulho e Preconceito',1986,2,'Ficção Cientifica'),
(30,'Clube da Luta',1972,2,'Ação');



insert into video values (1,1,1,'VHS',1.00),
(2,1,0,'DVD',1.00),
(3,2,1,'DVD',1.00),
(4,2,1,'DVD',3.00),
(5,3,1,'DVD',3.00),
(6,4,1,'DVD',3.00),
(7,4,1,'DVD',2.00),
(8,4,1,'DVD',1.00),
(9,5,0,'DVD',1.00),
(10,5,0,'VHS',2.00),
(11,5,1,'DVD',4.00),
(12,6,0,'DVD',3.00),
(13,6,0,'DVD',1.00),
(14,6,1,'DVD',2.00),
(15,7,0,'DVD',1.00),
(16,7,1,'DVD',4.00),
(17,7,0,'DVD',3.00),
(18,8,1,'DVD',2.00),
(19,9,1,'VHS',3.00),
(20,10,1,'DVD',1.00),
(21,10,0,'VHS',1.00),
(22,10,0,'VHS',4.00),
(23,11,1,'DVD',4.00),
(24,11,1,'DVD',1.00),
(25,12,1,'DVD',2.00),
(26,13,1,'DVD',1.00),
(27,14,1,'DVD',2.00),
(28,15,1,'VHS',4.00),
(29,15,1,'VHS',2.00),
(30,16,0,'VHS',2.00),
(31,17,0,'DVD',1.00),
(32,18,1,'DVD',3.00),
(33,18,1,'DVD',4.00),
(34,18,0,'DVD',1.00),
(35,19,1,'DVD',4.00),
(36,20,1,'VHS',3.00),
(37,20,0,'VHS',3.00),
(38,20,1,'DVD',3.00),
(39,21,0,'DVD',2.00),
(40,21,1,'DVD',4.00),
(41,22,0,'DVD',4.00),
(42,22,0,'DVD',4.00),
(43,22,1,'DVD',3.00),
(44,23,0,'DVD',4.00),
(45,23,1,'DVD',4.00),
(46,24,1,'VHS',2.00),
(47,25,0,'VHS',4.00),
(48,26,0,'DVD',1.00),
(49,26,0,'DVD',2.00),
(50,26,0,'DVD',1.00),
(51,27,0,'DVD',1.00),
(52,28,1,'DVD',2.00),
(53,28,1,'DVD',3.00),
(54,29,1,'DVD',1.00),
(55,30,1,'VHS',3.00),
(56,30,0,'DVD',3.00);



insert into emprestimo values (1,13,'2018-09-19','2018-09-21',2.00,0),
(2,16,'2018-04-18','2018-04-22',1.00,0),
(3,2,'2018-04-09','2018-04-12',6.00,1),
(4,2,'2018-09-22','2018-09-26',2.00,1),
(5,16,'2018-07-24','2018-07-26',1.00,1),
(6,13,'2018-08-27','2018-09-01',1.00,0),
(7,8,'2018-03-31','2018-04-03',1.00,1),
(8,19,'2019-01-12','2019-01-17',8.00,1),
(9,17,'2018-12-17','2018-12-21',6.00,1),
(10,17,'2018-03-15','2018-03-16',1.00,0),
(11,11,'2018-06-24','2018-06-27',3.00,1),
(12,11,'2018-08-23','2018-08-24',5.00,0),
(13,15,'2018-04-20','2018-04-22',6.00,0),
(14,13,'2018-04-17','2018-04-20',9.00,1),
(15,13,'2018-09-11','2018-09-15',9.00,1),
(16,9,'2018-07-21','2018-07-26',9.00,1),
(17,15,'2018-06-24','2018-06-25',7.00,0),
(18,4,'2018-04-25','2018-04-27',4.00,0),
(19,7,'2018-03-27','2018-04-01',6.00,0),
(20,5,'2018-04-18','2018-04-23',4.00,1),
(21,7,'2018-07-31','2018-08-04',2.00,1),
(22,10,'2018-05-14','2018-05-18',4.00,0),
(23,13,'2018-03-03','2018-03-06',1.00,0),
(24,19,'2018-12-05','2018-12-09',7.00,0),
(25,16,'2018-07-10','2018-07-12',2.00,0),
(26,5,'2018-09-22','2018-09-27',6.00,1),
(27,1,'2018-07-23','2018-07-27',2.00,1),
(28,7,'2018-08-11','2018-08-12',3.00,0),
(29,1,'2018-12-03','2018-12-05',5.00,0),
(30,3,'2018-08-27','2018-09-01',4.00,0),
(31,16,'2018-10-03','2018-10-07',2.00,1),
(32,9,'2018-03-26','2018-03-28',6.00,1),
(33,18,'2018-12-25','2018-12-30',2.00,0),
(34,12,'2018-05-27','2018-05-29',6.00,1),
(35,13,'2018-04-23','2018-04-26',8.00,1),
(36,7,'2018-04-17','2018-04-22',5.00,0),
(37,18,'2018-02-24','2018-02-27',2.00,0),
(38,4,'2018-05-17','2018-05-20',1.00,0),
(39,15,'2018-06-04','2018-06-05',4.00,0),
(40,7,'2018-04-20','2018-04-24',9.00,0),
(41,17,'2018-03-14','2018-03-16',7.00,0),
(42,16,'2018-12-15','2018-12-19',1.00,0),
(43,2,'2018-09-22','2018-09-23',5.00,1),
(44,10,'2018-10-23','2018-10-27',7.00,0),
(45,10,'2018-04-05','2018-04-08',7.00,1),
(46,10,'2018-02-12','2018-02-17',3.00,0),
(47,6,'2019-01-06','2019-01-08',7.00,0),
(48,12,'2019-02-02','2019-02-06',4.00,0),
(49,13,'2018-06-02','2018-06-05',1.00,0),
(50,6,'2018-10-25','2018-10-26',8.00,0),
(51,8,'2018-02-05','2018-02-10',6.00,0),
(52,2,'2018-08-22','2018-08-23',2.00,0),
(53,19,'2018-04-10','2018-04-11',4.00,0),
(54,2,'2019-01-27','2019-01-29',7.00,1),
(55,4,'2018-11-25','2018-11-30',6.00,0),
(56,2,'2018-12-17','2018-12-22',4.00,1),
(57,12,'2018-07-16','2018-07-20',2.00,1),
(58,10,'2018-11-12','2018-11-15',4.00,0),
(59,2,'2018-07-19','2018-07-21',2.00,0),
(60,10,'2019-01-11','2019-01-12',4.00,0),
(61,15,'2018-04-22','2018-04-23',3.00,0),
(62,9,'2018-07-05','2018-07-07',7.00,1),
(63,6,'2018-05-14','2018-05-18',4.00,1),
(64,6,'2018-12-25','2018-12-30',2.00,0),
(65,15,'2018-05-11','2018-05-16',7.00,1),
(66,13,'2018-09-16','2018-09-20',4.00,0),
(67,12,'2019-01-17','2019-01-20',4.00,1),
(68,19,'2018-11-14','2018-11-19',4.00,1),
(69,6,'2018-10-24','2018-10-28',6.00,0),
(70,15,'2018-06-10','2018-06-11',9.00,0),
(71,10,'2019-01-06','2019-01-09',2.00,1),
(72,10,'2018-11-11','2018-11-16',6.00,1),
(73,4,'2018-02-11','2018-02-12',6.00,0),
(74,19,'2018-10-26','2018-10-30',2.00,1),
(75,16,'2018-09-25','2018-09-26',8.00,0),
(76,16,'2018-12-15','2018-12-17',3.00,0),
(77,6,'2019-01-02','2019-01-04',4.00,1),
(78,11,'2018-04-05','2018-04-08',2.00,1),
(79,7,'2019-01-05','2019-01-10',6.00,1),
(80,13,'2018-05-15','2018-05-17',7.00,1),
(81,12,'2018-08-20','2018-08-21',2.00,0),
(82,3,'2018-08-19','2018-08-24',5.00,0),
(83,8,'2018-09-22','2018-09-23',4.00,1),
(84,2,'2018-08-25','2018-08-29',8.00,1),
(85,15,'2018-05-29','2018-06-02',7.00,0),
(86,7,'2018-10-06','2018-10-09',4.00,1),
(87,9,'2018-08-03','2018-08-04',6.00,0),
(88,9,'2018-09-23','2018-09-25',1.00,1),
(89,18,'2018-05-28','2018-05-31',7.00,0),
(90,17,'2018-04-26','2018-04-30',6.00,1),
(91,13,'2018-09-02','2018-09-04',9.00,1),
(92,18,'2018-06-09','2018-06-14',8.00,0),
(93,19,'2018-08-24','2018-08-29',9.00,1),
(94,1,'2018-05-19','2018-05-24',4.00,1),
(95,16,'2018-09-17','2018-09-20',4.00,1),
(96,18,'2018-08-05','2018-08-08',4.00,1),
(97,1,'2018-10-28','2018-10-31',6.00,0),
(98,14,'2018-02-19','2018-02-21',8.00,1),
(99,18,'2018-11-20','2018-11-23',2.00,1),
(100,19,'2018-10-02','2018-10-07',2.00,1),
(101,20,'2018-03-03','2018-03-05',7.00,0);



insert into emprestimo_video values (2,1),
(72,1),
(93,1),
(99,1),
(1,2),
(2,2),
(13,2),
(21,2),
(32,2),
(41,2),
(52,2),
(62,2),
(97,2),
(13,3),
(19,3),
(58,3),
(91,3),
(5,4),
(18,4),
(19,4),
(26,4),
(42,4),
(68,4),
(84,4),
(85,4),
(31,5),
(59,5),
(101,5),
(11,6),
(34,6),
(57,6),
(3,7),
(22,7),
(27,7),
(56,7),
(4,8),
(20,8),
(33,8),
(34,8),
(54,8),
(56,8),
(74,8),
(86,8),
(87,8),
(89,8),
(52,9),
(59,9),
(86,9),
(90,9),
(10,10),
(61,10),
(80,10),
(82,10),
(83,10),
(48,11),
(3,12),
(16,12),
(30,12),
(31,12),
(40,12),
(43,12),
(25,13),
(33,13),
(94,13),
(11,14),
(46,14),
(71,14),
(94,14),
(15,15),
(63,15),
(95,15),
(42,16),
(68,16),
(7,17),
(40,17),
(75,17),
(87,17),
(100,17),
(101,17),
(12,18),
(15,18),
(49,18),
(53,18),
(66,18),
(67,18),
(9,19),
(21,19),
(87,19),
(91,19),
(58,20),
(99,21),
(27,22),
(76,22),
(30,23),
(32,23),
(84,23),
(21,24),
(71,24),
(48,25),
(88,25),
(15,26),
(16,26),
(36,26),
(90,26),
(49,27),
(77,27),
(6,28),
(65,28),
(17,29),
(54,29),
(73,29),
(79,29),
(80,29),
(10,30),
(12,30),
(35,30),
(39,30),
(49,30),
(65,30),
(12,31),
(41,31),
(57,31),
(20,32),
(27,32),
(35,32),
(51,32),
(28,33),
(29,33),
(92,33),
(1,34),
(17,34),
(47,34),
(96,34),
(14,35),
(33,35),
(47,35),
(59,35),
(69,35),
(81,35),
(97,35),
(67,36),
(4,37),
(10,38),
(42,38),
(92,38),
(60,39),
(77,39),
(97,39),
(101,39),
(8,40),
(11,40),
(19,40),
(23,40),
(24,40),
(72,40),
(31,41),
(96,41),
(100,41),
(32,42),
(38,42),
(41,42),
(46,42),
(96,42),
(64,43),
(89,43),
(34,44),
(36,44),
(39,44),
(88,44),
(13,45),
(50,45),
(55,45),
(70,45),
(79,45),
(86,45),
(84,46),
(95,46),
(98,46),
(8,47),
(98,47),
(37,48),
(63,48),
(94,48),
(99,49),
(6,50),
(25,50),
(30,50),
(46,50),
(62,50),
(63,50),
(68,51),
(78,51),
(85,51),
(88,51),
(6,52),
(57,52),
(92,52),
(93,52),
(95,52),
(7,53),
(24,53),
(44,53),
(65,53),
(98,53),
(18,54),
(76,54),
(79,54),
(23,55),
(45,55),
(48,55),
(4,56),
(60,56);
