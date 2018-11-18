package edu.ifma.dcomp.laboratorio03.dao;

import edu.ifma.dcomp.laboratorio03.modelo.Cliente;

import java.sql.*;

final public class ClienteDao {

    final private Connection conexao;

    public ClienteDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void salva(Cliente cliente) {
        String sql = "insert into clientes (nome, cpf, endereco, telefone) values (?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf());
            stm.setString(3, cliente.getEndereco());
            stm.setString(4, cliente.getTelefone());

            stm.execute();

            try (ResultSet key = stm.getGeneratedKeys()) {
                key.next();
                int id = key.getInt(1);
                cliente.setId(id);
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public Cliente recupera(Integer id) {
        String sql = "select * from clientes where id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, id);

            ResultSet clienteComId = stm.executeQuery();
            clienteComId.next();

            return this.constroiCliente(clienteComId);
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void trunca() {
        String sql = "truncate clientes restart identity cascade";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    private Cliente constroiCliente(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String cpf = rs.getString("cpf");
        String endereco = rs.getString("endereco");
        String telefone = rs.getString("telefone");

        final Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
        cliente.setId(id);

        return cliente;
    }
}
