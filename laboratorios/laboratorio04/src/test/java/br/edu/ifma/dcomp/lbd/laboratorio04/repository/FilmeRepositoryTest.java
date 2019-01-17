package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FilmeRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test");
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        filmeRepository = new FilmeRepository(entityManager);
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
    public void deveSalvarFilme() {
        final Filme filme = new Filme();
        filme.setTitulo("Seja o que Deus quiser");
        filme.setAnoDeLancamento(2019);
        filme.setDuracao(2);
        filme.setGenero("Drama");

        filmeRepository.salva(filme);
        entityManager.flush();

        Assert.assertTrue(filme.getId() != null);
    }

    @Test
    public void deveConsultarFilmePorId() {
        final Filme filme = FilmeBuilder.umFilme().constroi();

        filmeRepository.salva(filme);
        entityManager.flush();

        final Filme filmeBusca = filmeRepository.buscaPorId(filme.getId());

        Assert.assertTrue(filmeBusca.equals(filme));
    }

    @Test
    public void deveConsutarFilmePorTitulo() {
        final Filme filme1 = FilmeBuilder.umFilme().comTitulo("Até que a Sorte nos Separe").constroi();
        final Filme filme2 = FilmeBuilder.umFilme().comTitulo("Até que a Sorte nos Separe 2").constroi();

        filmeRepository.salva(filme1);
        filmeRepository.salva(filme2);
        entityManager.flush();

        final List<Filme> filmeBusca1 = filmeRepository.buscaPorTitulo("Até que a sorte nos Separe");
        final List<Filme> filmeBusca2 = filmeRepository.buscaPorTitulo("Até que a sorte nos Separe 2");


        Assert.assertEquals(2, filmeBusca1.size());
        Assert.assertEquals(1, filmeBusca2.size());
    }

    @Test
    public void consultaDeFilmePorTituloQuandoNaoHaFilmeCadastradoDeveRetornarListaVazia() {
        final List<Filme> listaDeFilmesVazia = filmeRepository.buscaPorTitulo("Até que a sorte nos Separe");

        Assert.assertEquals(0, listaDeFilmesVazia.size());
    }

    @Test
    public void deveAtualizarTituloDoFilme() {
        final Filme filme = FilmeBuilder.umFilme().constroi();

        filmeRepository.salva(filme);
        entityManager.flush();

        final String novoTitulo = "Seja o que Deus quiser 2";

        filme.setTitulo(novoTitulo);

        filmeRepository.atualiza(filme);

        final Filme filmePId = filmeRepository.buscaPorId(filme.getId());

        Assert.assertEquals(novoTitulo, filmePId.getTitulo());
    }

    @Test
    public void deveRemoverFilme() {
        final Filme filme = FilmeBuilder.umFilme().constroi();

        filmeRepository.salva(filme);
        entityManager.flush();

        filmeRepository.exclui(filme);

        Assert.assertEquals(null, filmeRepository.buscaPorId(filme.getId()));
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveRemoverFilmeQuandoEstePossuiVideosAssociados() {
        final Filme filme = FilmeBuilder.umFilme().constroi();
        final Video video1 = VideoBuilder.umVideo().comFilme(filme).constroi();

        filme.adicionar(video1);

        filmeRepository.salva(filme);

        VideoRepository videoRepository = new VideoRepository(entityManager);
        videoRepository.salva(video1);
        entityManager.flush();

        filmeRepository.exclui(filme);
        entityManager.flush();
    }

}
