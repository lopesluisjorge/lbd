package teste.cli.dao.filme;

import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;

import java.sql.Connection;
import java.util.Scanner;

final public class TesteAtualizaFilme {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            FilmeDao filmeDao = new FilmeDao(conexao);

            System.out.println("Digite o ID do filme: ");
            Integer id = teclado.nextInt();

            Filme filme = filmeDao.recupera(id);

            System.out.println("Digite o novo nome do filme: ");
            teclado.next();
            String titulo = teclado.nextLine();

            System.out.println("Genero: ");
            String genero = teclado.nextLine();

            System.out.println("Ano de Lançamento: ");
            Integer anoDeLancamento = teclado.nextInt();

            System.out.println("Duração (inteiro): ");
            Integer duracao = teclado.nextInt();

            filme.setTitulo(titulo);
            filme.setGenero(genero);
            filme.setAnoDeLancamento(anoDeLancamento);
            filme.setDuracao(duracao);
            filmeDao.atualiza(filme);

            System.out.println("ID: " + filme.getId());
            System.out.println("Filme atualizado com sucesso!");
        }
    }

}
