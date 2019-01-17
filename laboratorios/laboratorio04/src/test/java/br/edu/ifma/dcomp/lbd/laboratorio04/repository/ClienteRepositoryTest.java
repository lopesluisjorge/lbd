package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.ClienteBuilder;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private ClienteRepository clienteRepository;

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test");
    }

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        clienteRepository = new ClienteRepository(entityManager);
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
    public void deveInserirNovoCliente() {
        final Cliente cliente = new Cliente();
        cliente.setNome("Jon");
        cliente.setCpf("12345678910");
        cliente.setEndereco(ClienteBuilder.umEndereco());
        cliente.setTelefone("(98) 988887777");

        clienteRepository.salva(cliente);
        entityManager.flush();

        Assert.assertTrue(cliente.getId() != null);
    }

    @Test
    public void deveRetornarUmClienteNaConsultaPorIdCasoCadatrado() {
        final Cliente cliente = ClienteBuilder.umcliente().constroi();

        clienteRepository.salva(cliente);
        entityManager.flush();

        final Cliente clienteBusca = clienteRepository.buscaPorId(cliente.getId());

        Assert.assertTrue(clienteBusca.equals(cliente));
    }

    @Test
    public void deveRetornarClienteComCpfCadastrado() {
        final Cliente cliente = ClienteBuilder.umcliente().comCpf("12345678910").constroi();

        clienteRepository.salva(cliente);
        entityManager.flush();

        final Cliente clienteBusca = clienteRepository.buscaPorCpf(cliente.getCpf());

        Assert.assertTrue(clienteBusca.equals(cliente));
    }

    @Test
    public void deveAtualizarONomeDoCliente() {
        final Cliente cliente = ClienteBuilder.umcliente().constroi();

        clienteRepository.salva(cliente);
        entityManager.flush();

        final String novoNome = "James";
        cliente.setNome(novoNome);

        clienteRepository.atualiza(cliente);
        entityManager.flush();

        final Cliente clientePId = clienteRepository.buscaPorId(cliente.getId());

        Assert.assertEquals(novoNome, clientePId.getNome());
    }

    @Test
    public void deveRemoverOCliente() {
        final Cliente cliente = ClienteBuilder.umcliente().constroi();

        clienteRepository.salva(cliente);
        entityManager.flush();

        clienteRepository.exclui(cliente);

        Assert.assertEquals(null, clienteRepository.buscaPorId(cliente.getId()));
    }

}
