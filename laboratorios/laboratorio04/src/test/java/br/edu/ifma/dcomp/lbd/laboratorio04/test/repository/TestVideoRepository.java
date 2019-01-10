package br.edu.ifma.dcomp.lbd.laboratorio04.test.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.FilmeRepository;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.VideoRepository;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.support.EMTestInstantiator;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class TestVideoRepository {

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;
    private VideoRepository videoRepository;

    @BeforeEach
    private void setUp() {
        entityManager = EMTestInstantiator.getEntityManager();
        filmeRepository = new FilmeRepository(entityManager);
        videoRepository = new VideoRepository(entityManager);

        entityManager.createQuery("from Video").getResultList().forEach(video -> {
            videoRepository.exclui((Video) video);
        });
    }

    @AfterEach
    private void tearDown() {
        entityManager.close();
    }

    @Test
    public void testDeveSalvarVideo() {
        final Filme filme = new FilmeBuilder().build();

        filmeRepository.salva(filme);

        final Video video = new Video();
        video.setFilme(filme);
        video.setStatus(1);
        video.setTipo("DVD");
        video.setValorDaDiaria(new BigDecimal(1.99));

        videoRepository.salva(video);

        Assertions.assertTrue(filme.getId() != null);
    }

    @Test
    public void testDeveConsultarVideo() {
        final Video video = new VideoBuilder().build();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);

        Assertions.assertTrue(video.getId() != null);

        int idVideo = video.getId();

        Video vigeoBusca = videoRepository.buscaPorId(idVideo);

        Assertions.assertTrue(vigeoBusca.equals(video));
    }

    @Test
    public void testSeAtualizaOVideoNoBanco() {
        final Video video = new VideoBuilder().build();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);

        Assertions.assertTrue(video.getId() != null);

        final Filme novoFilme = new FilmeBuilder()
                .comTitulo("Seja o que Deus quiser 2")
                .comDuracao(3)
                .comAnoDeLancamento(2020)
                .build();

        video.setFilme(novoFilme);

        videoRepository.atualiza(video);

        final Video videoPId = videoRepository.buscaPorId(video.getId());

        Assertions.assertEquals(novoFilme, videoPId.getFilme());
    }

    @Test
    public void testDeveRemoverOVideo() {
        final Video video = new VideoBuilder().build();

        filmeRepository.salva(video.getFilme());
        videoRepository.salva(video);

        Assertions.assertTrue(video.getId() != null);

        Integer idfilme = video.getId();

        videoRepository.exclui(video);

        Assertions.assertEquals(null, videoRepository.buscaPorId(idfilme));
    }

}
