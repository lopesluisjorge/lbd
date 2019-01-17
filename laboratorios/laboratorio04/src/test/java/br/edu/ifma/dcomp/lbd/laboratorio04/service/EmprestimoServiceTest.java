package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.ClienteBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.*;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmprestimoServiceTest {

    private static EntityManagerFactory entityManagerFactory;

    private ClienteService clienteService;
    private FilmeService filmeService;
    private VideoService videoService;
    private EmprestimoService emprestimoService;

    private EntityManager entityManager;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test_service");
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        clienteService = new ClienteService(entityManager);
        filmeService = new FilmeService(entityManager);
        videoService = new VideoService(entityManager);
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
    public void deveEmprestarVideo() {
        final Cliente cliente = ClienteBuilder.umcliente().constroi();
        clienteService.adiciona(cliente);

        final Filme filme1 = FilmeBuilder.umFilme().comTitulo("Filme 1").constroi();
        final Filme filme2 = FilmeBuilder.umFilme().comTitulo("Filme 2").constroi();

        final Video video1 = VideoBuilder.umVideo().comFilme(filme1).comTipo(TipoVideo.DVD).constroi();
        final Video video2 = VideoBuilder.umVideo().comFilme(filme1).comTipo(TipoVideo.VHS).constroi();
        final Video video3 = VideoBuilder.umVideo().comFilme(filme2).constroi();

        filmeService.adiciona(filme1);
        filmeService.adiciona(filme2);

        videoService.adiciona(video1);
        videoService.adiciona(video2);
        videoService.adiciona(video3);

        emprestimoService = new EmprestimoService(entityManager, cliente);
        emprestimoService.aloca(video1, video3);

        Assert.assertEquals(StatusVideo.EMPRESTADO, video1.getStatus());
        Assert.assertEquals(StatusVideo.EMPRESTADO, video3.getStatus());

        Assert.assertEquals(1, cliente.getEmprestimos().size());
    }

}
