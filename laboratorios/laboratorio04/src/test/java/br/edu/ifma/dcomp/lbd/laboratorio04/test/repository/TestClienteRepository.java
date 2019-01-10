package br.edu.ifma.dcomp.lbd.laboratorio04.test.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.ClienteRepository;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.builder.ClienteBuilder;
import br.edu.ifma.dcomp.lbd.laboratorio04.test.support.EMTestInstantiator;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TestClienteRepository {

    private EntityManager entityManager;
    private ClienteRepository clienteRepository;

    @BeforeEach
    private void setUp() {
        entityManager = EMTestInstantiator.getEntityManager();
        clienteRepository = new ClienteRepository(entityManager);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createNativeQuery("delete from cliente where id > 0").executeUpdate();
        transaction.commit();
    }

    @AfterEach
    private void tearDown() {
        entityManager.close();
    }

    @Test
    public void testSeInsereNovoClienteNoBanco() {
        final Cliente cliente = new Cliente();
        cliente.setNome("Jon");
        cliente.setCpf("12345678910");
        cliente.setEndereco("R. S. Antonio");
        cliente.setTelefone("(98) 9 8888-7777");

        clienteRepository.salva(cliente);

        Assertions.assertTrue(cliente.getId() != null);
    }

    @Test
    public void testSeOClienteFoiRetornadoNaConsulta() {
        final Cliente cliente = new ClienteBuilder().build();

        clienteRepository.salva(cliente);

        Assertions.assertTrue(cliente.getId() != null);

        int idCliente = cliente.getId();

        Cliente clienteBusca = clienteRepository.buscaPorId(idCliente);

        Assertions.assertTrue(clienteBusca.equals(cliente));
    }

    @Test
    public void testSeAtualizaONomeDoClienteNoBanco() {
        final Cliente cliente = new ClienteBuilder().build();

        clienteRepository.salva(cliente);

        final String novoNome = "James";

        cliente.setNome(novoNome);

        clienteRepository.atualiza(cliente);

        final Cliente clientePId = clienteRepository.buscaPorId(cliente.getId());

        Assertions.assertEquals(novoNome, clientePId.getNome());
    }

    @Test
    public void testDeveRemoverOCliente() {
        final Cliente cliente = new ClienteBuilder().build();

        clienteRepository.salva(cliente);

        Integer idCliente = cliente.getId();

        Assertions.assertTrue(idCliente != null);

        clienteRepository.exclui(cliente);

        Assertions.assertEquals(null, clienteRepository.buscaPorId(idCliente));
    }

}
