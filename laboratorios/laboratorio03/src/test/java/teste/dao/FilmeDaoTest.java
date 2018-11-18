package teste.dao;

import edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import edu.ifma.dcomp.laboratorio03.infra.PoolDeConexoes;
import edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import edu.ifma.dcomp.laboratorio03.modelo.Filme;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

public class FilmeDaoTest extends TestCase {

    private FilmeDao filmeDAO;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PoolDeConexoes pool = new PoolPostgres();
        filmeDAO = new FilmeDao(pool.getConexao());
        filmeDAO.trunca();
    }

    public void testDeveAdicionarFilme() {
        Filme filme = new Filme("Star Wars: Episódio IV - Uma Nova Esperança", 1977, 3, "Ação");

        filmeDAO.salva(filme);

        assertEquals(1, (int) filme.getId());
    }

    public void testDeveAtualizarFilme() {
        String nomeOriginal = "Star Wars: Episódio IV - Uma Nova Esperança";
        String nomeNovo = "Star Wars: Episódio V - O Império Contra-Ataca";

        Filme filme = new Filme(nomeOriginal, 1977, 3, "Ação");

        filmeDAO.salva(filme);

        Filme filmeAtualizado = new Filme(nomeNovo, 1980, 3, "Ação");
        filmeAtualizado.setId(1);

        filmeDAO.atualiza(filmeAtualizado);

        Filme filmePersistido = filmeDAO.recupera(1);

        assertFalse(filmePersistido.getTitulo().equals(nomeOriginal));
        assertEquals(nomeNovo, filmePersistido.getTitulo());
    }

    public void testDeveListarfilmes() {
        ArrayList<Filme> filmes = new ArrayList<>();

        filmes.add(new Filme("Star Wars: Episódio IV - Uma Nova Esperança", 1977, 3, "Ação"));
        filmes.add(new Filme("Os Caçadores da Arca Perdida", 1981, 2, "Aventura"));
        filmes.add(new Filme("Star Wars: Episódio V - O Império Contra-Ataca", 1980, 3, "Ação"));
        filmes.add(new Filme("O Senhor dos Anéis: A Sociedade do Anel", 2001, 3, "Aventura"));
        filmes.add(new Filme("Star Wars: Episódio VI - O Retorno do Jedi", 1983, 3, "Ação" ));

        filmes.forEach(filme -> filmeDAO.salva(filme));

        ArrayList<Filme> listagemDeFilmes = (ArrayList<Filme>) filmeDAO.lista();
        assertEquals(5, listagemDeFilmes.size());
    }

    public static Test suite(){
        return new TestSuite(FilmeDaoTest.class);
    }

    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }
}
