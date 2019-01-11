package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FilmeRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;

    @BeforeAll
    private static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test");
    }

    @BeforeEach
    private void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        filmeRepository = new FilmeRepository(entityManager);
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
    public void testDeveSalvarFilme() {
        final Filme filme = new Filme();
        filme.setTitulo("Seja o que Deus quiser");
        filme.setAnoDeLancamento(2019);
        filme.setDuracao(2);
        filme.setGenero("Drama");

        filmeRepository.salva(filme);
        entityManager.flush();

        Assertions.assertTrue(filme.getId() != null);
    }

    @Test
    public void testDeveConsultarFilme() {
        final Filme filme = FilmeBuilder.umFilme().constroi();

        filmeRepository.salva(filme);
        entityManager.flush();

        final Filme filmeBusca = filmeRepository.buscaPorId(filme.getId());

        Assertions.assertTrue(filmeBusca.equals(filme));
    }

    @Test
    public void deveAtualizarOTituloDoFilme() {
        final Filme filme = FilmeBuilder.umFilme().constroi();

        filmeRepository.salva(filme);
        entityManager.flush();

        final String novoTitulo = "Seja o que Deus quiser 2";

        filme.setTitulo(novoTitulo);

        filmeRepository.atualiza(filme);

        final Filme filmePId = filmeRepository.buscaPorId(filme.getId());

        Assertions.assertEquals(novoTitulo, filmePId.getTitulo());
    }

    @Test
    public void testDeveRemoverOFilme() {
        final Filme filme = FilmeBuilder.umFilme().constroi();

        filmeRepository.salva(filme);
        entityManager.flush();

        filmeRepository.exclui(filme);

        Assertions.assertEquals(null, filmeRepository.buscaPorId(filme.getId()));
    }

}
