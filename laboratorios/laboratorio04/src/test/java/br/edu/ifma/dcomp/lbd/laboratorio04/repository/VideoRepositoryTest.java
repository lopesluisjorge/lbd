package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class VideoRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;
    private VideoRepository videoRepository;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test");
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        filmeRepository = new FilmeRepository(entityManager);
        videoRepository = new VideoRepository(entityManager);;
    }

    @After
    public void tearDown() {
        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    @AfterClass
    public static void tearDownAll() {
        entityManagerFactory.close();
    }

    @Test
    public void deveSalvarVideo() {
        final Filme filme = FilmeBuilder.umFilme().constroi();
        final Video video = new Video();
        video.setFilme(filme);
        video.setStatus(1);
        video.setTipo("DVD");
        video.setValorDaDiaria(new BigDecimal(1.99));

        filmeRepository.salva(filme);
        videoRepository.salva(video);
        entityManager.flush();

        Assert.assertTrue(filme.getId() != null);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveSalvarVideoSemFilmeAtrelado() {
        final Video video = VideoBuilder.umVideo().comFilme(null).constroi();

        videoRepository.salva(video);
        entityManager.flush();
    }

    @Test
    public void deveConsultarVideo() {
        final Video video = VideoBuilder.umVideo().constroi();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);
        entityManager.flush();

        Video vigeoBusca = videoRepository.buscaPorId(video.getId());

        Assert.assertTrue(vigeoBusca.equals(video));
    }

    @Test
    public void deveAtualizarVideoNoBanco() {
        final Video video = VideoBuilder.umVideo().constroi();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);
        entityManager.flush();

        final Filme novoFilme = FilmeBuilder
                .umFilme()
                .comTitulo("Seja o que Deus quiser 2")
                .comDuracao(3)
                .comAnoDeLancamento(2020)
                .constroi();

        video.setFilme(novoFilme);

        videoRepository.atualiza(video);

        final Video videoPId = videoRepository.buscaPorId(video.getId());

        Assert.assertEquals(novoFilme, videoPId.getFilme());
    }

    @Test
    public void deveRemoverVideo() {
        final Video video = VideoBuilder.umVideo().constroi();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);
        entityManager.flush();

        Integer idfilme = video.getId();

        videoRepository.exclui(video);

        Assert.assertEquals(null, videoRepository.buscaPorId(idfilme));
    }

}
