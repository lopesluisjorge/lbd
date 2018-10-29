use academico;

/*
 * 1) Obtenha os nomes dos alunos, sexo e idade.
 */

select nomeal Nome, sexo, idade
from alunos
order by nomeal;

/*
 * 2) Todos os distintos pares (sexo, idade) de alunos.
 */

select distinct sexo, idade
from alunos;

/* 
 * 3) Todos os alunos de 22 anos.
 */

select nomeal Nome
from alunos
where idade = 22;

/* 
 * 4) Nomes dos alunos, nomes das disciplinas por eles cursadas e notas obtidas.
 */

select distinct a.nomeal Nome, d.descdisc Disciplina, h.nota
from alunos a
    join historico h   on a.matricula = h.matricula
    join professores p on h.coddisc = p.coddisc
    join disciplinas d on p.coddisc = d.coddisc;

/*
 * 5) Cursos, sexos e idades de alunos (sem repetição).
 */

select distinct d.descdisc Curso, a.sexo, a.idade
from alunos a
    join historico h   on a.matricula = h.matricula
    join professores p on h.coddisc = p.coddisc
    join disciplinas d on p.coddisc = d.coddisc
order by d.descdisc;

/* 
 * 6) Codigo das disciplinas e notas, das entradas em historico com notas entre 6 e 8.
 * OBS: usar nota between 6 and 8.
 */

select distinct d.coddisc Código, h.nota
from disciplinas d
    join professores p on d.coddisc = p.coddisc
    join historico h   on p.coddisc = h.coddisc
where nota between 6 and 8;

/*
 * 7) Denominacao das disciplinas de 04 créditos e nível < 5.
 * OBS: para selecionar disciplinas de 04 créditos, usar codigo like '%-04'
 */

select distinct descdisc Denominação
from disciplinas
where coddisc like '%-04' and nivel < 5;

/* 
 * 8) Professores e suas disciplinas (com eliminacao de duplicatas).
 */

select distinct p.professor 'Nome do Professor',
                d.descdisc 'Nome da Disciplina'
from professores p
    join disciplinas d on p.coddisc = d.coddisc
order by p.Professor, d.descdisc;


/* 9) Denominação das disciplinas, número de alunos que a cursaram no semestre
 * 1999/1 e média das notas das disciplinas.
 */

select d.descdisc Denominação, 
       count(*) 'Cursaram', 
       round(avg(h.nota), 2) 'Média'
from disciplinas d
    join professores p on d.coddisc = p.coddisc
    join historico h   on p.coddisc = h.coddisc
    join alunos a      on h.matricula = a.matricula
where h.ano_sem = '1999/1'
group by d.descdisc;

/*
 * 10) Nome das disciplinas onde somente alunos do sexo masculino a cursaram.
 */

select distinct d.descdisc
from disciplinas d
    join professores p on d.coddisc = p.coddisc
    join historico h   on p.codDisc = h.CodDisc
    join alunos a      on a.matricula = h.matricula
where d.CodDisc not in (select d.CodDisc
                        from disciplinas d
                            join professores p on d.coddisc = p.coddisc
                            join historico h   on p.CodDisc = h.CodDisc
                            join alunos a      on a.Matricula = h.Matricula
                        where a.Sexo = 'F');

/*
 * 11) Disciplinas que tem “Banco” em sua denominação.
 */

select distinct descdisc Denominacao from disciplinas
where descdisc like '%banco%';

/*
 * 12) Professores e nomes de seus alunos, para os professores que ministram
 * disciplinas de nível (período) maior do que 4 e para alunos do sexo feminino.
 */

select distinct p.professor, a.nomeal
from disciplinas d
    join professores p on d.coddisc = p.coddisc
    join historico h   on p.coddisc = h.coddisc
    join alunos a      on h.matricula = a.matricula
where a.sexo = 'F' and p.professor in 
    (select distinct p.professor
        from disciplinas d
    join professores p on d.coddisc = p.coddisc
    where d.nivel > 4);

/*
 * 13) Para cada professor, em cada ano-semestre, média de notas de seus alunos.
 */

select p.professor Professor,
       h.ano_sem 'Ano/Semestre', 
       avg(h.nota)
from professores p
    join historico h on p.coddisc = h.coddisc
    join alunos a    on h.matricula = a.matricula
group by p.professor, h.ano_sem;

/*
 * 14) Para cada aluno, seu nome, número de entradas de seu histórico e média das
 * notas nas disciplinas por ele cursadas.
 */
 
select a.nomeal 'Nome do Aluno', 
       count(*) 'Entradas no Histórico', 
       round(avg(h.nota), 2) 'Média das Notas'
from alunos a 
    join historico h on a.matricula = h.matricula
group by a.nomeal;
