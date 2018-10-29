
create database academico;
use academico;

Create table disciplinas(
      CodDisc  char(8) primary key,
      DescDisc char(50) not null,
      Nivel int(2));


Create table alunos(
    Matricula int(8) primary key,
    NomeAl    char(25) not null,
    Sexo      char(1) not null,
    Idade     int(2) not null,
    Curso     char(5) not null);

Create table professores(
    CodDisc   char(8),
    Turma     char(3),
    Ano_sem   char(6),
    Professor char(50) not null,
    Primary key(CodDisc,Turma,Ano_sem),
    Foreign key(CodDisc) references disciplinas(Coddisc));


Create table historico(
    Matricula int(8),
    CodDisc   char(8),
    Turma     char(3) not null,
    Ano_sem   char(6) not null,
    Nota      float(3,1) not null,
    Primary key(matricula,CodDisc,ano_sem),
    Foreign key(matricula) references alunos(matricula),
    Foreign key(CodDisc,Turma,Ano_sem) references professores(CodDisc,Turma,Ano_sem));


Create table historico_escolar(
     CodDisc     char(8),
     Denominacao char(50),
     Turma       char(3),
     Ano_sem     char(6),
     Professor   char(50),
     Matricula   int(8),
     NomeAl      char(25),
     Sexo        char(1),
     Idade       int(2),
     Curso       char(5),
     Nota        float(3,1));


-- ---------------------corte -----------------------

Insert into disciplinas values('46250-02','Sistemas de Informacao',3);
Insert into disciplinas values('46251-04','Engenharia de Software I',4);
Insert into disciplinas values('46252-04','Engenharia de Software II',6);
Insert into disciplinas values('46256-04','Banco de Dados I',4);
Insert into disciplinas values('46257-04','Banco de Dados II',6);
Insert into disciplinas values('46258-04','Laboratório de Bando de Dados',5);
Insert into disciplinas values('46266-04','Topicos Especiais em Bancos de Dados',7);
Insert into disciplinas values('46267-04','Topicos Especiais em Engenharia de Software I',7);
Insert into disciplinas values('46268-04','Topicos Especiais em Engenharia de Software II',8);
Insert into disciplinas values('46285-04','Engenharia de Software I',4);
Insert into disciplinas values('46286-04','Banco de Dados I',4);
Insert into alunos values(93106842,'João Carlos','F',26,'2/520');
Insert into alunos values(94103839,'Eduardo da Silva','M',22,'4/601');
Insert into alunos values(94108293,'Marcelo da Silva','M',22,'4/601');
Insert into alunos values(94112046,'Tiago da Silva','M',22,'4/601');
Insert into alunos values(94112192,'Vanessa da Silva','F',23,'4/601');
Insert into alunos values(94201018,'Carla da Silva','F',21,'4/601');
Insert into alunos values(94206067,'Rogerio da Silva','M',22,'4/601');
Insert into alunos values(95280018,'Solange da Silva','F',26,'4/601');
Insert into alunos values(95280023,'Marcelo da Silva','M',23,'4/601');
Insert into alunos values(95280027,'Katia da Silva','F',20,'4/601');
Insert into alunos values(96104543,'Marcos da Silva','M',26,'2/520');


-- ---------------------corte -----------------------

Insert into professores values('46250-02','128','1997/2','Ana Paula');
Insert into professores values('46250-02','128','1998/1','Ana Paula');
Insert into professores values('46250-02','128','1998/2','Ana Paula');
Insert into professores values('46250-02','128','1999/1','Ana Paula');
Insert into professores values('46250-02','128','1999/2','Ana Paula');
Insert into professores values('46250-02','128','2000/1','Ana Paula');
Insert into professores values('46250-02','138','1999/1','Ana Paula');
Insert into professores values('46250-02','138','2000/1','Ana Paula');
Insert into professores values('46251-04','128','1998/1','Yamaguti');
Insert into professores values('46251-04','128','1998/2','Yamaguti');
Insert into professores values('46251-04','128','1999/2','Bastos');
Insert into professores values('46251-04','128','2000/1','Bastos');
Insert into professores values('46251-04','138','1998/1','Afonso');
Insert into professores values('46251-04','138','1999/2','Yamaguti');
Insert into professores values('46251-04','138','2000/1','Yamaguti');
Insert into professores values('46252-04','128','1999/1','Afonso');
Insert into professores values('46252-04','128','1999/2','Afonso');
Insert into professores values('46252-04','128','2000/1','Afonso');
Insert into professores values('46256-04','128','1998/1','Karin');
Insert into professores values('46256-04','128','1998/2','Karin');
Insert into professores values('46286-04','128','1999/1','Karin');
Insert into professores values('46286-04','128','1999/2','Karin');
Insert into professores values('46286-04','128','2000/1','Karin');
Insert into professores values('46256-04','138','1999/1','Diogo');
Insert into professores values('46256-04','138','1999/2','Diogo');
Insert into professores values('46256-04','138','2000/1','Diogo');
Insert into professores values('46257-04','128','1999/1','Arruda');
Insert into professores values('46257-04','128','1999/2','Arruda');
Insert into professores values('46257-04','128','2000/1','Arruda');
Insert into professores values('46258-04','128','1998/2','Hubert');
Insert into professores values('46258-04','128','1999/1','Hubert');
Insert into professores values('46258-04','128','2000/1','Hubert');
Insert into professores values('46266-04','128','1999/2','Egidio');
Insert into professores values('46266-04','128','2000/1','Arruda');
Insert into professores values('46267-04','128','1999/2','Arruda');
commit;

-- ---------------------corte -----------------------

Insert into historico values(94108293,'46250-02','128','1997/2',6);
Insert into historico values(94112046,'46250-02','128','1997/2',6);
Insert into historico values(94112192,'46250-02','128','1997/2',8);
Insert into historico values(94103839,'46250-02','128','1998/1',9);
Insert into historico values(95280023,'46250-02','128','1998/1',9);
Insert into historico values(93106842,'46250-02','128','1998/2',8);
Insert into historico values(94206067,'46250-02','128','1998/2',9);
Insert into historico values(94201018,'46250-02','128','1999/1',8);
Insert into historico values(95280018,'46250-02','138','1999/1',9);
Insert into historico values(95280027,'46250-02','128','1999/1',4);
Insert into historico values(95280027,'46250-02','128','1999/2',7);
Insert into historico values(94108293,'46251-04','138','1998/1',6);
Insert into historico values(94112046,'46251-04','128','1998/1',5);
Insert into historico values(94112192,'46251-04','128','1998/1',7);
Insert into historico values(94103839,'46251-04','128','1998/2',6);
Insert into historico values(94201018,'46251-04','128','1999/2',8);
Insert into historico values(94206067,'46251-04','138','1999/2',8);
Insert into historico values(94108293,'46252-04','128','1999/1',9);
Insert into historico values(94112046,'46252-04','128','1999/2',9);
Insert into historico values(94112192,'46252-04','128','1999/2',8);
Insert into historico values(94108293,'46256-04','128','1998/1',8);
Insert into historico values(94112046,'46256-04','128','1998/1',8);
Insert into historico values(94112192,'46256-04','128','1998/1',3);
Insert into historico values(94103839,'46256-04','128','1998/2',5);
Insert into historico values(94112192,'46256-04','128','1998/2',7);
Insert into historico values(93106842,'46256-04','138','1999/1',7);
Insert into historico values(94206067,'46256-04','138','1999/1',9);
Insert into historico values(95280023,'46286-04','128','1999/1',7);
Insert into historico values(94201018,'46256-04','138','1999/2',8);
Insert into historico values(95280018,'46286-04','128','1999/2',8);
Insert into historico values(94108293,'46257-04','128','1999/1',7);
Insert into historico values(94112046,'46257-04','128','1999/1',9);
Insert into historico values(94112192,'46257-04','128','1999/2',6);
Insert into historico values(94108293,'46258-04','128','1998/2',8);
Insert into historico values(94112046,'46258-04','128','1998/2',7);
Insert into historico values(94103839,'46258-04','128','1999/1',7);
Insert into historico values(94112192,'46258-04','128','1999/1',5);
Insert into historico values(94108293,'46266-04','128','1999/2',7);
Insert into historico values(94112046,'46266-04','128','1999/2',6);
Insert into historico values(94108293,'46267-04','128','1999/2',8);

-- ---------------------corte -----------------------

Insert into historico_escolar values(null,null,null,null,null,96104543,'Marcos da Silva','M',26,'2/520',null);
Insert into historico_escolar values('46286-04','Banco de Dados I',null,null,null,null,null,null,null,null,null);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1998/1','Karin',94108293,'Marcelo da Silva','M',22,'4/601',8);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1998/1','Karin',94112046,'Tiago da Silva','M',22,'4/601',8);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1998/1','Karin',94112192,'Vanessa da Silva','F',23,'4/601',3);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1998/2','Karin',94103839,'Eduardo da Silva','M',22,'4/601',5);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1998/2','Karin',94112192,'Vanessa da Silva','F',23,'4/601',7);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1999/1','Karin',95280023,'Marcelo da Silva','M',23,'4/601',7);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','1999/2','Karin',95280018,'Solange da Silva','F',26,'4/601',8);
Insert into historico_escolar values('46256-04','Banco de Dados I','128','2000/1','Karin',null,null,null,null,null,null);
Insert into historico_escolar values('46256-04','Banco de Dados I','138','1999/1','Diogo',93106842,'João Carlos','F',26,'2/520',7);
Insert into historico_escolar values('46256-04','Banco de Dados I','138','1999/1','Diogo',94206067,'Rogerio da Silva','M',22,'4/601',9);
Insert into historico_escolar values('46256-04','Banco de Dados I','138','1999/2','Diogo',94201018,'Carla da Silva','F',21,'4/601',8);
Insert into historico_escolar values('46256-04','Banco de Dados I','138','2000/1','Diogo',null,null,null,null,null,null);
Insert into historico_escolar values('46257-04','Banco de Dados II','128','1999/1','Arruda',94108293,'Marcelo da Silva','M',22,'4/601',7);
Insert into historico_escolar values('46257-04','Banco de Dados II','128','1999/1','Arruda',94112046,'Tiago da Silva','M',22,'4/601',9);


-- ---------------------corte -----------------------

Insert into historico_escolar values('46257-04','Banco de Dados II','128','1999/2','Arruda',94112192,'Vanessa da Silva','F',23,'4/601',6);
Insert into historico_escolar values('46257-04','Banco de Dados II','128','2000/1','Arruda',null,null,null,null,null,null);
Insert into historico_escolar values('46285-04','Engenharia de Software I',null,null,null,null,null,null,null,null,null);
Insert into historico_escolar values('46251-04','Engenharia de Software I','128','1998/1','Yamaguti',94112046,'Tiago da Silva','M',22,'4/601',5);
Insert into historico_escolar values('46251-04','Engenharia de Software I','128','1998/1','Yamaguti',94112192,'Vanessa da Silva','F',23,'4/601',7);
Insert into historico_escolar values('46251-04','Engenharia de Software I','128','1998/2','Yamaguti',94103839,'Eduardo da Silva','M',22,'4/601',6);
Insert into historico_escolar values('46251-04','Engenharia de Software I','128','1999/2','Bastos',94201018,'Carla da Silva','F',21,'4/601',8);
Insert into historico_escolar values('46251-04','Engenharia de Software I','128','2000/1','Bastos',null,null,null,null,null,null);
Insert into historico_escolar values('46251-04','Engenharia de Software I','138','1998/1','Afonso',94108293,'Marcelo da Silva','M',22,'4/601',6);
Insert into historico_escolar values('46251-04','Engenharia de Software I','138','1999/2','Yamaguti',94206067,'Rogerio da Silva','M',22,'4/601',8);
Insert into historico_escolar values('46251-04','Engenharia de Software I','138','2000/1','Yamaguti',null,null,null,null,null,null);
Insert into historico_escolar values('46252-04','Engenharia de Software II','128','1999/1','Afonso',94108293,'Marcelo da Silva','M',22,'4/601',9);
Insert into historico_escolar values('46252-04','Engenharia de Software II','128','1999/2','Afonso',94112046,'Tiago da Silva','M',22,'4/601',9);
Insert into historico_escolar values('46252-04','Engenharia de Software II','128','1999/2','Afonso',94112192,'Vanessa da Silva','F',23,'4/601',8);
Insert into historico_escolar values('46252-04','Engenharia de Software II','128','2000/1','Afonso',null,null,null,null,null,null);
Insert into historico_escolar values('46258-04','Laboratório de Bando de Dados','128','1998/2','Hubert',94108293,'Marcelo da Silva','M',22,'4/601',8);
Insert into historico_escolar values('46258-04','Laboratório de Bando de Dados','128','1998/2','Hubert',94112046,'Tiago da Silva','M',22,'4/601',7);
Insert into historico_escolar values('46258-04','Laboratório de Bando de Dados','128','1999/1','Hubert',94103839,'Eduardo da Silva','M',22,'4/601',7);

-- ---------------------corte -----------------------

Insert into historico_escolar values('46258-04','Laboratório de Bando de Dados','128','1999/1','Hubert',94112192,'Vanessa da Silva','F',23,'4/601',5);
Insert into historico_escolar values('46258-04','Laboratório de Bando de Dados','128','2000/1','Hubert',null,null,null,null,null,null);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1997/2','Ana Paula',94108293,'Marcelo da Silva','M',22,'4/601',6);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1997/2','Ana Paula',94112046,'Tiago da Silva','M',22,'4/601',6);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1997/2','Ana Paula',94112192,'Vanessa da Silva','F',23,'4/601',8);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1998/1','Ana Paula',94103839,'Eduardo da Silva','M',22,'4/601',9);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1998/1','Ana Paula',95280023,'Marcelo da Silva','M',23,'4/601',9);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1998/2','Ana Paula',93106842,'João Carlos','F',26,'2/520',8);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1998/2','Ana Paula',94206067,'Rogerio da Silva','M',22,'4/601',9);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1999/1','Ana Paula',94201018,'Carla da Silva','F',21,'4/601',8);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1999/1','Ana Paula',95280027,'Katia da Silva','F',20,'4/601',4);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','1999/2','Ana Paula',95280027,'Katia da Silva','F',20,'4/601',7);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','128','2000/1','Ana Paula',null,null,null,null,null,null);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','138','1999/1','Ana Paula',95280018,'Solange da Silva','F',26,'4/601',9);
Insert into historico_escolar values('46250-02','Sistemas de Informacao','138','2000/1','Ana Paula',null,null,null,null,null,null);
Insert into historico_escolar values('46266-04','Topicos Especiais em Bancos de Dados','128','1999/2','Egidio',94108293,'Marcelo da Silva','M',22,'4/601',7);
Insert into historico_escolar values('46266-04','Topicos Especiais em Bancos de Dados','128','1999/2','Egidio',94112046,'Tiago da Silva','M',22,'4/601',6);
Insert into historico_escolar values('46266-04','Topicos Especiais em Bancos de Dados','128','2000/1','Arruda',null,null,null,null,null,null);
Insert into historico_escolar values('46267-04','Topicos Especiais em Engenharia de Software I','128','1999/2','Arruda',94108293,'Marcelo da Silva','M',22,'4/601',8);
Insert into historico_escolar values('46268-04','Topicos Especiais em Engenharia de Software II',null,null,null,null,null,null,null,null,null);
