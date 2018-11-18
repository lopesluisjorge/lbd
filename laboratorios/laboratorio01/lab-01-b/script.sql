use universidade;

-- I. Obter o os nomes dos professores que são do departamento denominado 'Informática', sejam
-- doutores, e que, em 932 (2o semestre de 1993), ministraram alguma turma de disciplina do
-- departamento 'Informática' que tenha mais que três créditos.

select distinct pr.nomeprof 'Nome do Professor'
from professor pr join departamento dep on pr.coddepto  = dep.coddepto 
				  join titulacao tit    on pr.codtit    = tit.codtit
                  join disciplina disc  on dep.coddepto = disc.coddepto
				  join turma tur 	    on disc.numdisc = tur.numdisc and dep.coddepto = tur.coddepto
where dep.nomedepto = 'Informática' and tit.nometit = 'Doutor' 
									and tur.anosem = 932 
									and tur.coddepto = 'INF01'
                                    and disc.creditosdisc > 3;



-- II. Obter os nomes das disciplinas do departamento denominado 'Informática' que não foram
-- oferecidas no semestre 932 (2o semestre de 1993). Resolver a questão com consultas aninhadas

select nomedisc 'Disciplina' from disciplina
where coddepto in (select coddepto 
                   from departamento 
				   where nomedepto = 'Informática') 
	and numdisc not in (select numdisc
						from turma 
						where anosem = 932);



-- Os dados se apresentaram insuficientes para obter um resultado satisfatório. 
-- Logo, foi utilizado o ano 941 para teste da consulta

select nomedisc 'Disciplina' from disciplina
where coddepto in (select coddepto 
                   from departamento 
				   where nomedepto = 'Informática') 
	and numdisc not in (select numdisc
						from turma 
						where anosem = 941);

-- A quantidade total de disciplinas no departamento de informática

select count(*) from disciplina where coddepto = 'INF01';

-- A quantidade de disciplinas ministradas em 932 

select count(distinct nomedisc) from disciplina dis 
	join departamento d on d.coddepto = dis.coddepto 
    join turma t        on t.numdisc  = dis.numdisc
where nomedepto = 'Informática' and anosem = 932;

-- A quantidade de disciplinas ministradas em 941

select count(distinct nomedisc) from disciplina dis 
	join departamento d on d.coddepto = dis.coddepto 
    join turma t        on t.numdisc  = dis.numdisc
where nomedepto = 'Informática' and anosem = 941;



-- III. Obter os identificadores de todas turmas de disciplinas do departamento denominado
-- 'Informática' que não têm aula na sala de número 102 do prédio de código 43425.

select distinct t.siglatur Sigla from turma t
	join disciplina disc  on t.numdisc     = disc.numdisc
    join departamento dep on disc.coddepto = dep.coddepto
where dep.nomedepto = 'Informática' 
	and t.siglatur not in (select siglatur from horario h
							   join sala s on h.numsala = s.numsala
                           where s.codpredio = 43425 and s.numsala = 102 
													 and h.coddepto = 'INF01');

-- Os dados na tabela não foram suficientes para elucidar a resposta da consulta.
-- Todas as turmas não tem aula na sala 102 do prédio 43425 pois este não está cadastrado, 
-- como visto na consulta.

select codpredio from predio where codpredio = 43425;

-- Portanto, foi ralizado teste com os dados de um prédio cadastrado (43421):

select distinct t.siglatur Sigla from turma t
	join disciplina disc  on t.numdisc     = disc.numdisc
    join departamento dep on disc.coddepto = dep.coddepto
where dep.nomedepto = 'Informática' 
	and t.siglatur not in (select siglatur from horario h
							   join sala s on h.numsala = s.numsala
                           where s.codpredio = 43421 and s.numsala = 102 
													 and h.coddepto = 'INF01');

-- Todas as turmas de informática

select distinct siglatur from turma where coddepto = 'INF01';

-- Turmas de informática que tem aula na sala 102 do prédio 43421

select distinct siglatur from horario h
	join sala s   on h.numsala = s.numsala
where s.codpredio = 43421 and s.numsala = 102 and h.coddepto = 'INF01';



-- V. Obter o número de salas que foram usadas no ano-semestre 001 por turmas do departamento
-- denominado 'Informática'.

select count(distinct s.numsala) 'Quantidade de Salas' from sala s
where numsala in (select numsala from horario h
	     join turma t            on h.numdisc     = t.numdisc
	     join disciplina disc    on t.numdisc     = disc.numdisc
	     join departamento depto on disc.coddepto = depto.coddepto
where t.anosem = 932 and depto.nomedepto = 'Informática');

select count(distinct s.numsala) 'Quantidade de Salas' from sala s
where s.numsala in
    (select numsala from horario
	 where numdisc in 
        (select numdisc from turma
		 where anosem = 932 and numdisc in 
			(select numdisc from disciplina 
			 where coddepto in 
				(select coddepto from departamento 
                 where nomedepto = 'Informática'))));



-- VI. Obter os nomes das disciplinas do departamento denominado 'Informática' que têm o maior
-- número de créditos dentre as disciplinas deste departamento. Resolver com consulta aninhada.

select nomedisc Disciplina from disciplina
where coddepto in (select coddepto from departamento 
                   where nomedepto = 'Informática')
			   and creditosdisc = (select max(creditosdisc) from disciplina);



-- VII. Para cada departamento, obter seu nome e o número de disciplinas do departamento. Obter o
-- resultado em ordem descendente de número de créditos.

select nomedepto Departamento, count(*) 'Quantidade de Disciplinas'
from departamento dep
	join disciplina disc on dep.coddepto = disc.coddepto
group by nomedepto;
