package edu.ifma.dcomp.laboratorio03.dao;

import edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.math.BigDecimal;
import java.sql.*;

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

    public void trunca() {
        final String sql = "truncate videos restart identity cascade";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

}
