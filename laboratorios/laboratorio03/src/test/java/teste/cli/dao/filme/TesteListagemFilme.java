package teste.cli.dao.filme;

import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;

import java.sql.Connection;

final public class TesteListagemFilme {

    public static void main(String[] args) throws Exception {

        try (Connection conexao = new PoolPostgres().getConexao()) {
            FilmeDao filmeDao = new FilmeDao(conexao);

            filmeDao.lista().forEach(filme -> {
                System.out.println("ID: " + filme.getId() + "\n");

                System.out.println("Titulo: " + filme.getTitulo());
                System.out.println("Ano de Lançamento: " + filme.getAnoDeLancamento());
                System.out.println("Duração: " + filme.getDuracao());
                System.out.println("Genero: " + filme.getGenero() + "\n\n");
            });
        }

    }

}
