package br.edu.ifma.dcomp.laboratorio03.dao;

import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;
import br.edu.ifma.dcomp.laboratorio03.modelo.Emprestimo;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

final public class EmprestimoDao {

    private Connection conexao;

    public EmprestimoDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void realizaLocacao(Cliente cliente, ArrayList<Video> videos) {
        String sqlSoliciatacaoEmprestimo = "insert into emprestimos (cliente_id, data_locacao, status) values (?, current_date, 1)";

        if (!verificarDisponibilidadeDosVideos(videos)) {
            throw new RuntimeException("Alguns vídeos já estão emprestados");
        }

        int idEmprestimo;
        int idEmprestimoCopia = 0;

        try (PreparedStatement stmEmprestimo = conexao.prepareStatement(sqlSoliciatacaoEmprestimo,
                Statement.RETURN_GENERATED_KEYS)) {
            conexao.setAutoCommit(false);

            stmEmprestimo.setInt(1, cliente.getId());
            stmEmprestimo.execute();

            try (ResultSet chaves = stmEmprestimo.getGeneratedKeys()) {
                chaves.next();
                idEmprestimo = chaves.getInt(1);
                idEmprestimoCopia = idEmprestimo;

                String sqlSolicitacaoVideo = "insert into emprestimo_video (emprestimo_id, video_id) values (?, ?)";
                String sqlAtualizaStatusVideoParaEmprestado = "update videos set status = 2 where id = ?";

                videos.forEach(video -> {
                    try (PreparedStatement stmVideo = conexao.prepareStatement(sqlSolicitacaoVideo)) {
                        stmVideo.setInt(1, idEmprestimo);
                        stmVideo.setInt(2, video.getId());
                        stmVideo.execute();
                    } catch (SQLException err) {
                        tentaRollback(err);
                    }

                    try (PreparedStatement stmAtualizaStatus = conexao.prepareStatement(sqlAtualizaStatusVideoParaEmprestado)) {
                        stmAtualizaStatus.setInt(1, video.getId());
                        stmAtualizaStatus.execute();
                    } catch (SQLException err) {
                        tentaRollback(err);
                    }

                    video.setStatus(Video.LOCADO);
                });
            }

            conexao.commit();
            conexao.setAutoCommit(true);
        } catch (SQLException err) {
            tentaRollback(err);
        }

        String sqlSelectEmprestimo = "select e.id, data_locacao, status from emprestimos e where e.id = ?";

        try (PreparedStatement stmSelectEmprestimos = conexao.prepareStatement(sqlSelectEmprestimo)) {
            stmSelectEmprestimos.setInt(1, idEmprestimoCopia);
            ResultSet rs = stmSelectEmprestimos.executeQuery();

            rs.next();
            Date dataLocacao = rs.getDate("data_locacao");
            boolean status = rs.getBoolean("status");

            Emprestimo emprestimo = new Emprestimo(cliente, dataLocacao.toLocalDate(), null, null, status);
            cliente.adiciona(emprestimo);
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    public void realizaDevolucao(Emprestimo emprestimo) {
        String sqlDevolucao = "update emprestimos " +
                "set " +
                "data_devolucao = current_date, " +
                "valor_aluguel = ? * (current_date - (select data_locacao from emprestimos where id = ?)) " +
                "where id = ?";

        try (PreparedStatement stmExecutarDevolucao = conexao.prepareStatement(sqlDevolucao)) {
            conexao.setAutoCommit(false);

            BigDecimal valorTotalDiaria = new BigDecimal(0);
            for (Video video : emprestimo.getVideos()) {
                valorTotalDiaria.add(video.getValorDaDiaria());
            }

            stmExecutarDevolucao.setBigDecimal(1, valorTotalDiaria);
            stmExecutarDevolucao.setInt(2, emprestimo.getId());
            stmExecutarDevolucao.setInt(3, emprestimo.getId());

            stmExecutarDevolucao.execute();

            String sqlAtualizaEstadoVideoParaDisponivel = "update videos set status = 1 where id = ?";

            emprestimo.getVideos().forEach(video -> {
                try (PreparedStatement stmAtualizaEstadoVideoParaDisponivel = conexao.prepareStatement(sqlAtualizaEstadoVideoParaDisponivel)) {
                    stmAtualizaEstadoVideoParaDisponivel.setInt(1, video.getId());
                    stmAtualizaEstadoVideoParaDisponivel.execute();
                } catch (SQLException err) {
                    tentaRollback(err);
                }
            });

            conexao.commit();
        } catch (SQLException err) {
            tentaRollback(err);
        }
    }

    public void trunca() {
        final String sqlEmprestimoVideo = "truncate emprestimo_video restart identity cascade";

        try (PreparedStatement stm = conexao.prepareStatement(sqlEmprestimoVideo)) {
            stm.execute();
        } catch (SQLException err) {
            throw new RuntimeException(err);
        }
    }

    private boolean verificarDisponibilidadeDosVideos(ArrayList<Video> videos) {
        int quantidadeVideosDisponiveis = videos.stream()
                .filter(video -> video.getStatus() == Video.DISPOSNIVEL)
                .collect(Collectors.toList())
                .size();

        return videos.size() == quantidadeVideosDisponiveis;
    }

    private void tentaRollback(SQLException err) {
        try {
            conexao.rollback();
        } catch (SQLException roll) {}
        throw new RuntimeException(err);
    }

}
