package br.edu.ifma.dcomp.laboratorio03.dao;

import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

final public class FilmeDao {

    final private Connection conexao;

    public FilmeDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void salva(Filme filme) {
        String sql = "insert into filmes (titulo, ano_lancamento, duracao, genero) values (?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, filme.getTitulo());
            stm.setInt(2, filme.getAnoDeLancamento());
            stm.setInt(3, filme.getDuracao());
            stm.setString(4, filme.getGenero());

            stm.execute();

            try (ResultSet keys = stm.getGeneratedKeys()) {
                keys.next();
                int id = keys.getInt(1);
                filme.setId(id);
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void atualiza(Filme filme) {
        String sql = "update filmes set titulo = ?, ano_lancamento = ?, duracao = ?, genero = ? where id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, filme.getTitulo());
            stm.setInt(2, filme.getAnoDeLancamento());
            stm.setInt(3, filme.getDuracao());
            stm.setString(4, filme.getGenero());
            stm.setInt(5, filme.getId());

            stm.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public Filme recupera(Integer id) {
        String sql = "select * from filmes where id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, id);

            ResultSet filmeComId = stm.executeQuery();

            filmeComId.next();

            return this.constroiFilme(filmeComId);
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public List<Filme> lista() {
        String sql = "select * from filmes";

        final ArrayList<Filme> listagemDeFilmes = new ArrayList<>();

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            ResultSet resultadoListagem = stm.executeQuery();

            while (resultadoListagem.next()) {
                listagemDeFilmes.add(constroiFilme(resultadoListagem));
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }

        return listagemDeFilmes;
    }

    public void trunca() {
        final String sql = "truncate filmes restart identity cascade";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    private Filme constroiFilme(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        Integer anoDeLancamento = rs.getInt("ano_lancamento");
        Integer duracao = rs.getInt("duracao");
        String genero = rs.getString("genero");

        final Filme filme = new Filme(titulo, anoDeLancamento, duracao, genero);
        filme.setId(id);

        return filme;
    }

}
