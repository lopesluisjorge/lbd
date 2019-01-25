package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.builder.ClienteBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.FilmeBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.VideoBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.*;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DevolucaoServiceTest {

    private static EntityManagerFactory entityManagerFactory;

    private ClienteService clienteService;
    private FilmeService filmeService;
    private VideoService videoService;
    private EmprestimoService emprestimoService;
    private DevolucaoService devolucaoService;

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
        emprestimoService = new EmprestimoService(entityManager);
        devolucaoService = new DevolucaoService(entityManager);
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
    public void deveDevolverVideoAposEmprestimo() {
        final Cliente cliente = ClienteBuilder.umcliente().comCpf("12345678910").constroi();
        clienteService.adiciona(cliente);

        final Filme filme1 = FilmeBuilder.umFilme().comTitulo("Filme 1").constroi();
        final Filme filme2 = FilmeBuilder.umFilme().comTitulo("Filme 2").constroi();

        int VALOR_VIDEO = 2;
        final Video video1 = VideoBuilder.umVideo().comFilme(filme1).comValorDaDiaria(new BigDecimal(VALOR_VIDEO)).comTipo(TipoVideo.DVD).constroi();
        final Video video2 = VideoBuilder.umVideo().comFilme(filme1).comValorDaDiaria(new BigDecimal(VALOR_VIDEO)).comTipo(TipoVideo.VHS).constroi();
        final Video video3 = VideoBuilder.umVideo().comFilme(filme2).comValorDaDiaria(new BigDecimal(VALOR_VIDEO)).constroi();

        filmeService.adiciona(filme1);
        filmeService.adiciona(filme2);

        videoService.adiciona(video1);
        videoService.adiciona(video2);
        videoService.adiciona(video3);

        int DIAS_EMPRESTADOS = 2;
        // Feito ontem
        Emprestimo emprestimo = emprestimoService.aloca(cliente, LocalDate.now().minusDays(DIAS_EMPRESTADOS), video1, video3);


        devolucaoService.devolve(emprestimo);
        emprestimo.calculaValorDoAluguel();

        Assert.assertEquals(StatusEmprestimo.ATIVO, emprestimo.getStatus());
        Assert.assertEquals(new BigDecimal(DIAS_EMPRESTADOS + (VALOR_VIDEO + VALOR_VIDEO)), emprestimo.getValorDoAluguel());
        Assert.assertEquals(LocalDate.now(), emprestimo.getDataDeDevolucao());
    }


}
