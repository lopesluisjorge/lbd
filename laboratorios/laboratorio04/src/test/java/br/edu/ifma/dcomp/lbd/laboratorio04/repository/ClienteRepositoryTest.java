package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.builder.ClienteBuilder;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteRepositoryTest {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private ClienteRepository clienteRepository;

    @BeforeAll
    private static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("laboratorio04_test");
    }

    @BeforeEach
    private void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        clienteRepository = new ClienteRepository(entityManager);
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
    public void deveInserirNovoCliente() {
        final Cliente cliente = new Cliente();
        cliente.setNome("Jon");
        cliente.setCpf("12345678910");
        cliente.setEndereco("R. S. Antonio");
        cliente.setTelefone("(98) 988887777");

        clienteRepository.salva(cliente);
        entityManager.flush();

        Assertions.assertTrue(cliente.getId() != null);
    }

    @Test
    public void deveRetornarUmClienteNaConsultaPorIdCasoCadatrado() {
        final Cliente cliente = ClienteBuilder.umcliente().constroi();

        clienteRepository.salva(cliente);
        entityManager.flush();

        final Cliente clienteBusca = clienteRepository.buscaPorId(cliente.getId());

        Assertions.assertTrue(clienteBusca.equals(cliente));
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

        Assertions.assertEquals(novoNome, clientePId.getNome());
    }

    @Test
    public void deveRemoverOCliente() {
        final Cliente cliente = ClienteBuilder.umcliente().constroi();

        clienteRepository.salva(cliente);
        entityManager.flush();

        clienteRepository.exclui(cliente);

        Assertions.assertEquals(null, clienteRepository.buscaPorId(cliente.getId()));
    }

}
