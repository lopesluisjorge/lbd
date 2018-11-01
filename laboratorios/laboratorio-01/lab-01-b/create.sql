drop database if exists universidade;

create database universidade;

use universidade;

create table departamento (
    coddepto varchar(10) not null,
    nomedepto varchar(45) not null,
    primary key (coddepto));

create table disciplina (
    coddepto varchar(10) not null,
    numdisc int not null,
    nomedisc varchar(45) not null,
    creditosdisc int not null,
    primary key (coddepto, numdisc),
    foreign key (coddepto) references departamento(coddepto));

create table prereq (
    coddepto varchar(10) not null,
    numdisc int not null,
    coddeptoprereq varchar(10) not null,
    numdiscprereq int not null,
    primary key (coddepto, numdisc, coddeptoprereq, numdiscprereq),
    foreign key (coddepto, numdisc) references disciplina(coddepto, numdisc),
    foreign key (coddeptoprereq, numdiscprereq) references disciplina(coddepto, numdisc));

create table turma (
    anosem int not null,
    coddepto varchar(10),
    numdisc int not null,
    siglatur char(1) not null,
    capacidade int not null,
    primary key (anosem, coddepto, numdisc, siglatur),
    foreign key (coddepto, numdisc) references disciplina(coddepto, numdisc));

create table predio (
    codpredio int not null,
    descricaopredio varchar(45) not null,
    primary key (codpredio));

create table sala (
    codpredio int not null,
    numsala int not null,
    capacidade int,
    primary key (codpredio,numsala),
    foreign key (codpredio) references predio(codpredio));
    
alter table sala add column descricaosala varchar(45);

create table horario (
    anosem int not null,
    coddepto varchar(10),
    numdisc int not null,
    siglatur char(1) not null,
    diasem int not null,
    horainicio int not null,
    numhoras int not null,
    codpredio int not null,
    numsala int not null,
    primary key (anosem, coddepto, numdisc, siglatur, diasem, horainicio),
    foreign key (anosem, coddepto, numdisc, siglatur) references turma(anosem, coddepto, numdisc, siglatur),
    foreign key (codpredio, numsala) references sala(codpredio, numsala));
    
create table titulacao (
    codtit int not null,
    nometit varchar(45) not null,
    primary key (codtit));
    
create table professor (
    codprof int not null,
    nomeprof varchar(45) not null, 
    codtit int, 
    coddepto varchar(10) not null,
    primary key (codprof),
    foreign key (codtit) references titulacao(codtit),
    foreign key (coddepto) references departamento(coddepto));
    
create table professor_turma (
    anosem int not null,
    coddepto varchar(10) not null,
    numdisc int not null,
    siglatur char(1) not null,
    codprof int not null,
    primary key (anosem, coddepto, numdisc, siglatur, codprof),
    foreign key (anosem, coddepto, numdisc, siglatur) references turma(anosem, coddepto, numdisc, siglatur),
    foreign key (codprof) references professor(codprof));