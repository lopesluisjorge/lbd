package br.edu.ifma.dcomp.laboratorio03.dao;

import br.edu.ifma.dcomp.laboratorio03.construcao.ConstrutorFilme;
import br.edu.ifma.dcomp.laboratorio03.construcao.ConstrutorVideo;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

final public class VideoDao {

    private Connection conexao;

    public VideoDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void salva(Video video) {
        String sql = "insert into videos (filme_id, status, tipo, valor_diaria) values (?, ?, ?, ?)";

        try (PreparedStatement stm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, video.getFilme().getId());
            stm.setInt(2, video.getStatus());
            stm.setString(3, video.getTipo());
            stm.setBigDecimal(4, video.getValorDaDiaria());

            stm.execute();

            try (ResultSet key = stm.getGeneratedKeys()) {
                key.next();
                int id = key.getInt(1);
                video.setId(id);
            }
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public List<Video> listaVideosDisponiveis() {
        String sql = "select " +
                "v.id, v.status, v.tipo, v.valor_diaria, " +
                "f.id f_id, titulo, ano_lancamento, duracao, genero " +
                "from videos v " +
                "join filmes f on v.filme_id = f.id " +
                "where status = 1";

        final ArrayList<Video> listagemDeVideosDisponiveis = new ArrayList<>();

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            ResultSet resultados = stm.executeQuery();

            while (resultados.next()) {
                listagemDeVideosDisponiveis.add(constroiVideoComFilme(resultados));
            }

            return listagemDeVideosDisponiveis;
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public Video recupera(int id) {
        String sql = "select * from videos where id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setInt(1, id);

            ResultSet videoComId = stm.executeQuery();

            videoComId.next();

            return this.constroiVideo(videoComId);
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    private Video constroiVideo(ResultSet resultados) throws SQLException {
        int id = resultados.getInt("id");
        Integer status = resultados.getInt("status");
        String tipo = resultados.getString("tipo");
        BigDecimal valorDiaria = resultados.getBigDecimal("valor_diaria");

        return ConstrutorVideo.constroi(status, tipo, valorDiaria, id);
    }

    private Video constroiVideoComFilme(ResultSet resultados) throws SQLException {
        int idFilme = resultados.getInt("f_id");
        String tituloFilme = resultados.getString("titulo");
        int anoLancamentoFilme = resultados.getInt("ano_lancamento");
        int duracaoFilme = resultados.getInt("duracao");
        String generoFilme = resultados.getString("genero");


        Filme filme = ConstrutorFilme.constroi(tituloFilme, anoLancamentoFilme, duracaoFilme, generoFilme, idFilme);

        Video video = constroiVideo(resultados);
        video.setFilme(filme);

        return video;
    }

    public void trunca() {
        final String sql = "truncate videos restart identity cascade";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

}
