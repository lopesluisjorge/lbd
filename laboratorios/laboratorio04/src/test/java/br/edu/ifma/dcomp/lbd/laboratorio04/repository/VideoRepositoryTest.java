package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class VideoRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;
    private VideoRepository videoRepository;

    @BeforeAll
    private static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test");
    }

    @BeforeEach
    private void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        filmeRepository = new FilmeRepository(entityManager);
        videoRepository = new VideoRepository(entityManager);;
    }

    @AfterEach
    private void tearDown() {
        entityManager.getTransaction().rollback();
        entityManager.close();
    }

    @AfterAll
    private static void tearDownAll() {
        entityManagerFactory.close();
    }

    @Test
    public void testDeveSalvarVideo() {
        final Filme filme = FilmeBuilder.umFilme().constroi();
        final Video video = new Video();
        video.setFilme(filme);
        video.setStatus(1);
        video.setTipo("DVD");
        video.setValorDaDiaria(new BigDecimal(1.99));

        filmeRepository.salva(filme);
        videoRepository.salva(video);
        entityManager.flush();

        Assertions.assertTrue(filme.getId() != null);
    }

    @Test
    public void testDeveConsultarVideo() {
        final Video video = VideoBuilder.umVideo().constroi();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);
        entityManager.flush();

        Video vigeoBusca = videoRepository.buscaPorId(video.getId());

        Assertions.assertTrue(vigeoBusca.equals(video));
    }

    @Test
    public void testSeAtualizaOVideoNoBanco() {
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

        Assertions.assertEquals(novoFilme, videoPId.getFilme());
    }

    @Test
    public void testDeveRemoverOVideo() {
        final Video video = VideoBuilder.umVideo().constroi();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);
        entityManager.flush();

        Integer idfilme = video.getId();

        videoRepository.exclui(video);

        Assertions.assertEquals(null, videoRepository.buscaPorId(idfilme));
    }

}
