# Lab 01 - Consultas SQL - Parte 2

# Questões

1. Crie um BD chamado Universidade e defina as tabelas a seguir neste BD.

```
Departamento (coddepto, nomedepto)
- coddepto é chave primária

Disciplina (coddepto, numdisc, nomedisc, creditosdisc)
- o par (coddepto, numdisc) é chave primária;
- coddepto é uma chave estrangeira para departamento.

PreRequisito(coddepto, numdisc, coddeptoprereq, numdiscprereq)
- os atributos (coddepto, numdisc, coddeptoprereq, numdiscprereq) formam a chave primária;
- (coddepto, numdisc) é uma chave estrangeira para Disciplina
- (coddeptoprereq, numdiscprereq) é uma chave estrangeira para Disciplina

Turma(anosem, coddepto, numdisc, siglatur, capacidadeoferta)
- (anosem, coddepto, numdisc, siglatur) é chave primária;
- (coddepto, numdisc) é uma chave estrangeira para Disciplina.

Horario(anosem, coddepto, numdisc, siglatur, diasem, horainicio, numhoras, codpred, numsala)
- (anosem, coddepto, numdisc, siglatur, diasem, horainicio) é chave primária;
- (anosem, coddepto, numdisc, siglatur) é uma chave estrangeira para Turma.
- (codpred, numsala) é uma chave estrangeira para Sala.

Predio(codpredio, nomepred)
- codpred é chave primária;

Sala(codpred, numsala, capacsala)
- o par (codpred,numsala) é chave primária;
- codpred é uma chave estrangeira para Predio.

Professor(codprof, nomeprof, codtit, coddepto)
- codprof é chave primária;
- codtit é uma chave estrangeira para Titulação.
- coddepto é uma chave estrangeira para Departamento.

ProfTurma(anosem, coddepto, numdisc, siglatur, codprof)
- (anosem, coddepto, numdisc, siglatur, codprof) é chave primária;
- (anosem, coddepto, numdisc, siglatur) é uma chave estrangeira para Turma;
- codprof é uma chave estrangeira para Professor.

Titulacao(codtit, nometit)
- codtit é chave primária;
```

2. Use o script “insere-dados-bd-universidade.sql” para popular o banco de dados.

3. Resolva as seguintes consultas usando SQL.

- Obter o os nomes dos professores que são do departamento denominado 'Informática', sejam
doutores, e que, em 932 (2o semestre de 1993), ministraram alguma turma de disciplina do
departamento 'Informática' que tenha mais que três créditos.

- Obter os nomes das disciplinas do departamento denominado 'Informática' que não foram
oferecidas no semestre 932 (2o semestre de 1993). Resolver a questão com consultas aninhadas

- Obter os identificadores de todas turmas de disciplinas do departamento denominado
`Informática' que não têm aula na sala de número 102 do prédio de código 43425.

- Obter o número de salas que foram usadas no ano-semestre 001 por turmas do departamento
denominado `Informática'.

- Obter os nomes das disciplinas do departamento denominado `Informática' que têm o maior
número de créditos dentre as disciplinas deste departamento. Resolver com consulta aninhada.

- Para cada departamento, obter seu nome e o número de disciplinas do departamento. Obter o
resultado em ordem descendente de número de créditos.
