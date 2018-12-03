package teste.cli.dao.filme;

import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;

import java.sql.Connection;
import java.util.Scanner;

final public class TesteInsecaoFilme {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            FilmeDao filmeDao = new FilmeDao(conexao);

            System.out.println("Digite o nome do filme: ");
            String titulo = teclado.nextLine();

            System.out.println("Genero: ");
            String genero = teclado.nextLine();

            System.out.println("Ano de Lançamento: ");
            Integer anoDeLancamento = teclado.nextInt();

            System.out.println("Duração (inteiro): ");
            Integer duracao = teclado.nextInt();

            Filme filme = new Filme(titulo, anoDeLancamento, duracao, genero);

            filmeDao.salva(filme);

            System.out.println("ID: " + filme.getId());
        }
    }

}
