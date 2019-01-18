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
    public void deveEmprestarVideoDVD() {
        final Cliente cliente = ClienteBuilder.umcliente().comCpf("12345678910").constroi();
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

        emprestimoService = new EmprestimoService(entityManager);
        emprestimoService.aloca(cliente, video1, video3);

        Assert.assertEquals(StatusVideo.EMPRESTADO, video1.getStatus());
        Assert.assertEquals(StatusVideo.EMPRESTADO, video3.getStatus());

        Assert.assertEquals(1, cliente.getEmprestimos().size());
    }

    @Test
    public void deveEmprestarVideoVHS() {
        final Cliente cliente = ClienteBuilder.umcliente().comCpf("12345678911").constroi();
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

        emprestimoService = new EmprestimoService(entityManager);
        emprestimoService.aloca(cliente, video2);

        Assert.assertEquals(StatusVideo.EMPRESTADO, video2.getStatus());

        Assert.assertEquals(1, cliente.getEmprestimos().size());
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveEmprestarVideoSeClienteEstiverInativo() {
        final Cliente cliente = ClienteBuilder.umcliente().inativo().comCpf("12345678912").constroi();
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

        emprestimoService = new EmprestimoService(entityManager);
        emprestimoService.aloca(cliente, video1, video3);
    }

    @Test(expected = RuntimeException.class)
    public void naoDeveEmprestarVideoQuandoClienteEstiverComEmprestimosEmAtraso() {
        final Cliente cliente = ClienteBuilder.umcliente().inativo().comCpf("12345678913").constroi();
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


        emprestimoService = new EmprestimoService(entityManager);
        // Video Alocado a 4 dias
        emprestimoService.aloca(cliente, LocalDate.now().minusDays(Emprestimo.MAXIMO_DIAS_DO_EMPRESTIMO + 1), video1);

        emprestimoService.aloca(cliente, video2);
    }

}
