package teste.cli.dao.filme;

import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;

import java.sql.Connection;
import java.util.Scanner;

final public class TesteRecuperaFilme {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            FilmeDao filmeDao = new FilmeDao(conexao);

            System.out.println("Digite o ID do filme: ");
            int idFilme = teclado.nextInt();

            Filme filme = filmeDao.recupera(idFilme);

            System.out.println("Titulo: " + filme.getTitulo());
            System.out.println("Ano de Lançamento: " + filme.getAnoDeLancamento());
            System.out.println("Duração: " + filme.getDuracao());
            System.out.println("Genero: " + filme.getGenero());
        }
    }

}
