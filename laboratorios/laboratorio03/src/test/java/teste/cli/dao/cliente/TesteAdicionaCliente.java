package teste.cli.dao.cliente;

import br.edu.ifma.dcomp.laboratorio03.dao.ClienteDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;

import java.sql.Connection;
import java.util.Scanner;

final public class TesteAdicionaCliente {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            ClienteDao filmeDao = new ClienteDao(conexao);

            System.out.println("Digite o nome do cliente: ");
            String nome = teclado.nextLine();

            System.out.println("CPF: ");
            String cpf = teclado.nextLine();

            System.out.println("Endereço: ");
            String endereco = teclado.nextLine();

            System.out.println("Telefone: ");
            String telefone = teclado.nextLine();

            Cliente cliente = new Cliente(nome, cpf, endereco, telefone);

            filmeDao.salva(cliente);

            System.out.println("ID: " + cliente.getId());
        }
    }

}
