package teste.cli.dao.cliente;

import br.edu.ifma.dcomp.laboratorio03.dao.ClienteDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;

import java.sql.Connection;
import java.util.Scanner;

final public class TesteRecuperaCliente {

    public static void main(String[] args) throws  Exception {
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            ClienteDao clienteDao = new ClienteDao(conexao);

            System.out.println("Digite o ID do Cliente: ");
            int idFilme = teclado.nextInt();

            Cliente filme = clienteDao.recupera(idFilme);

            System.out.println("Nome: " + filme.getNome());
            System.out.println("CPF: " + filme.getCpf());
            System.out.println("Endereço: " + filme.getEndereco());
            System.out.println("Telefone: " + filme.getTelefone());
        }
    }

}
