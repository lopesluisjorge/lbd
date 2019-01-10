package br.edu.ifma.dcomp.lbd.laboratorio04.test.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.FilmeRepository;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.support.EMTestInstantiator;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;

public class TestFilmeRepository {

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;

    @BeforeEach
    private void setUp() {
        entityManager = EMTestInstantiator.getEntityManager();
        filmeRepository = new FilmeRepository(entityManager);

        entityManager.createQuery("from Filme").getResultList().forEach(filme -> filmeRepository.exclui((Filme) filme));
    }

    @AfterEach
    private void tearDown() {
        entityManager.close();
    }

    @Test
    public void testDeveSalvarFilme() {
        final Filme filme = new Filme();
        filme.setTitulo("Seja o que Deus quiser");
        filme.setAnoDeLancamento(2019);
        filme.setDuracao(2);
        filme.setGenero("Drama");

        filmeRepository.salva(filme);

        Assertions.assertTrue(filme.getId() != null);
    }

    @Test
    public void testDeveConsultarFilme() {
        final Filme filme = new FilmeBuilder().build();

        filmeRepository.salva(filme);

        Assertions.assertTrue(filme.getId() != null);

        int idFilme = filme.getId();

        Filme filmeBusca = filmeRepository.buscaPorId(idFilme);

        Assertions.assertTrue(filmeBusca.equals(filme));
    }

    @Test
    public void testSeAtualizaOTituloDoFilmeNoBanco() {
        final Filme filme = new FilmeBuilder().build();

        filmeRepository.salva(filme);

        Assertions.assertTrue(filme.getId() != null);

        final String novoTitulo = "Seja o que Deus quiser 2";

        filme.setTitulo(novoTitulo);

        filmeRepository.atualiza(filme);

        final Filme filmePId = filmeRepository.buscaPorId(filme.getId());

        Assertions.assertEquals(novoTitulo, filmePId.getTitulo());
    }

    @Test
    public void testDeveRemoverOFilme() {
        final Filme filme = new FilmeBuilder().build();

        filmeRepository.salva(filme);

        Assertions.assertTrue(filme.getId() != null);

        Integer idfilme = filme.getId();

        filmeRepository.exclui(filme);

        Assertions.assertEquals(null, filmeRepository.buscaPorId(idfilme));
    }

}
