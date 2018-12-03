package teste.unitario.dao;

import br.edu.ifma.dcomp.laboratorio03.dao.ClienteDao;
import br.edu.ifma.dcomp.laboratorio03.dao.EmprestimoDao;
import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.dao.VideoDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolDeConexoes;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;
import java.util.ArrayList;

final public class EmprestimoDaoTest extends TestCase {

    private EmprestimoDao emprestimoDAO;
    private PoolDeConexoes poolDeConexoes;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        poolDeConexoes = new PoolPostgres();
        emprestimoDAO = new EmprestimoDao(poolDeConexoes.getConexao());
        emprestimoDAO.trunca();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        emprestimoDAO.trunca();
    }

    public void testEmprestaLivro() throws Exception {
        ClienteDao clienteDao = new ClienteDao(poolDeConexoes.getConexao());
        clienteDao.trunca();
        Cliente cliente = new Cliente("Lorem", "12345678910", "R. S. Camilo", "988778877");
        clienteDao.salva(cliente);

        FilmeDao filmeDao = new FilmeDao(poolDeConexoes.getConexao());
        filmeDao.trunca();
        Filme filme = new Filme("Star Wars: Episódio IV - Uma Nova Esperança", 1977, 3, "Ação");
        filmeDao.salva(filme);

        VideoDao videoDao = new VideoDao(poolDeConexoes.getConexao());
        videoDao.trunca();
        final Video video = new Video(1, "DVD", new BigDecimal(10.00));
        video.setFilme(filmeDao.recupera(1));
        videoDao.salva(video);

        ArrayList<Video> videosLocados = new ArrayList<>();
        videosLocados.add(video);
        emprestimoDAO.realizaLocacao(cliente, videosLocados);

        assertEquals(1, cliente.getEmprestimos().size());
        assertEquals(Video.LOCADO, video.getStatus());
        // assertEquals(Emprestimo.ATIVO, cliente.getEmprestimos().get(1).getStatus());
    }

    public static Test suite(){
        return new TestSuite(EmprestimoDaoTest.class);
    }

    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }

}
