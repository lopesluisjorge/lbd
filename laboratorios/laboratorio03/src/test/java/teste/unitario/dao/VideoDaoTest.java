package teste.unitario.dao;

import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.dao.VideoDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolDeConexoes;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;
import java.sql.SQLException;

final public class VideoDaoTest extends TestCase {

    private PoolDeConexoes pool;
    private VideoDao videoDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        pool = new PoolPostgres();
        videoDao = new VideoDao(pool.getConexao());
        videoDao.trunca();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        videoDao.trunca();
    }

    public void testAdicionaVideo() throws SQLException {
        FilmeDao filmeDao = new FilmeDao(pool.getConexao());
        filmeDao.salva(new Filme("Star Wars: Episódio IV - Uma Nova Esperança", 1977, 3, "Ação"));

        final Video video = new Video(1, "DVD", new BigDecimal(10.00));
        video.setFilme(filmeDao.recupera(1));

        videoDao.salva(video);

        assertEquals(1, (int) video.getId());
        assertEquals(Video.DISPOSNIVEL, video.getStatus());
    }

    public static Test suite(){
        return new TestSuite(ClienteDaoTest.class);
    }

    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }

}
