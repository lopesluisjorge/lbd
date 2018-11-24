package teste.dao;

import br.edu.ifma.dcomp.laboratorio03.dao.ClienteDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolDeConexoes;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ClienteDaoTest extends TestCase {

    private ClienteDao clienteDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PoolDeConexoes pollDeConexoes = new PoolPostgres();
        clienteDao = new ClienteDao(pollDeConexoes.getConexao());
        clienteDao.trunca();
    }

    public void testAdicionarCliente() {
        Cliente cliente = new Cliente("Lorem", "12345678910", "R. S. Camilo", "988778877");

        clienteDao.salva(cliente);

        assertEquals(1, (int) cliente.getId());
    }

    public static Test suite(){
        return new TestSuite(ClienteDaoTest.class);
    }

    public static void main(String[] args){
        junit.textui.TestRunner.run(suite());
    }
}
