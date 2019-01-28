package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.ClienteBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.*;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class ClienteServiceTest {

    private static EntityManagerFactory entityManagerFactory;

    private ClienteService clienteService;

    private EntityManager entityManager;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test_service");
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        clienteService = new ClienteService(entityManager);
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @AfterClass
    public static void tearDownAll() {
        entityManagerFactory.close();
    }

    @Test
    public void deveAdicionarCliente() {
        Cliente cliente = ClienteBuilder.umcliente().constroi();

        clienteService.adiciona(cliente);

        Assert.assertTrue(cliente.getId() != null);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveRemoverCliente() {
        final Cliente cliente = ClienteBuilder.umcliente().comCpf("12345678913").constroi();
        clienteService.adiciona(cliente);

        final Filme filme1 = FilmeBuilder.umFilme().comTitulo("Filme 1").constroi();
        final Filme filme2 = FilmeBuilder.umFilme().comTitulo("Filme 2").constroi();

        final Video video1 = VideoBuilder.umVideo().comFilme(filme1).comTipo(TipoVideo.DVD).constroi();
        final Video video2 = VideoBuilder.umVideo().comFilme(filme1).comTipo(TipoVideo.VHS).constroi();
        final Video video3 = VideoBuilder.umVideo().comFilme(filme2).constroi();

        FilmeService filmeService = new FilmeService(entityManager);

        filmeService.adiciona(filme1);
        filmeService.adiciona(filme2);

        VideoService videoService = new VideoService(entityManager);

        videoService.adiciona(video1);
        videoService.adiciona(video2);
        videoService.adiciona(video3);

        EmprestimoService emprestimoService = new EmprestimoService(entityManager);
        // EmprestimoTest atrasado
        emprestimoService.aloca(cliente, LocalDate.now().minusDays(Emprestimo.MAXIMO_DIAS_DO_EMPRESTIMO + 1), video1);

        clienteService.remove(cliente);
    }
}
