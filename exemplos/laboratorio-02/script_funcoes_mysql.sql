create table notas(
    aluno varchar(10), 
    nota1 int,
    nota2 int,
    nota3 int,
    nota4 int
);


insert into notas values('Maria', 8, 9, 10, 6);
insert into notas values('Jose', 7, 8, 5, 9);


select * from notas;


delimiter |


create function media (nome varchar(10))
returns float
deterministic
begin
    declare n1,n2,n3,n4 int;
    declare med float;
    select nota1, nota2, nota3, nota4 into n1, n2, n3, n4
    from notas
    where aluno = nome;
    
    set med = (n1 + n2 + n3 + n4) / 4;
    return med;
end


delimiter ;


select  media('Maria');

select aluno, media(aluno) from notas;
