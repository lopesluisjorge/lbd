package br.edu.ifma.dcomp.laboratorio03.dao;

import br.edu.ifma.dcomp.laboratorio03.construcao.ConstrutorCliente;
import br.edu.ifma.dcomp.laboratorio03.construcao.ConstrutorVideo;
import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;
import br.edu.ifma.dcomp.laboratorio03.modelo.Emprestimo;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public Cliente recupera(int id) {
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

    public ArrayList<Cliente> listaClientesComEmprestimoAtivo() {
        String sql = "select " +
                "c.id, nome, cpf, endereco, telefone, " +
                "e.id e_id, data_locacao, data_devolucao, valor_aluguel, status, " +
                "v.id v_id, v.status v_status, tipo, valor_diaria " +
                "from clientes c " +
                "join emprestimos e on c.id = e.cliente_id " +
                "join emprestimo_video ev on e.id = ev.empestimo_id " +
                "join videos v on ev.video_id = v.id";

        final ArrayList<Cliente> listagemDeClientes = new ArrayList<>();

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            ResultSet resultadoListagem = stm.executeQuery();

            while (resultadoListagem.next()) {
                listagemDeClientes.add(constroiClienteComEmprestimoAtivo(resultadoListagem));
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }

        return listagemDeClientes;
    }

    public List<Cliente> lista() {
        String sql = "select * from clientes";

        final ArrayList<Cliente> listagemClientes = new ArrayList<>();

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            ResultSet resultados = stm.executeQuery();
            while (resultados.next()) {
                listagemClientes.add(this.constroiCliente(resultados));
            }

            return  listagemClientes;
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

        return ConstrutorCliente.constroi(nome, cpf, endereco, telefone, id);
    }

    private Cliente constroiClienteComEmprestimoAtivo(ResultSet rs) throws SQLException {
        int idEmprestimo = rs.getInt("e_id");
        LocalDate dataLocacao = rs.getDate("data_locacao").toLocalDate();
        BigDecimal valorAluguel = rs.getBigDecimal("valor_aluguel");
        boolean statusEmprestimo = rs.getBoolean("status");

        int idVideo = rs.getInt("v_id");
        int statusVideo = rs.getInt("v_status");
        String tipo = rs.getString("tipo");
        BigDecimal valorDiaria = rs.getBigDecimal("valor_diaria");


        Video video = ConstrutorVideo.constroi(statusVideo, tipo, valorDiaria, idVideo);

        Emprestimo emprestimo = new Emprestimo(dataLocacao, null, valorAluguel, statusEmprestimo);
        emprestimo.setId(idEmprestimo);
        emprestimo.adiciona(video);

        Cliente cliente = constroiCliente(rs);

        cliente.adiciona(emprestimo);

        return cliente;
    }

}
