package teste.cli.dao.emprestimo;

import br.edu.ifma.dcomp.laboratorio03.dao.ClienteDao;
import br.edu.ifma.dcomp.laboratorio03.dao.EmprestimoDao;
import br.edu.ifma.dcomp.laboratorio03.dao.VideoDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Cliente;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final public class TesteEfetuaLocacao {

    public static void main(String[] args) throws Exception {
        ClienteDao clienteDao;
        VideoDao videoDao;
        EmprestimoDao emprestimoDao;

        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            clienteDao = new ClienteDao(conexao);
            System.out.println("Clientes Cadastrados: \n");
            clienteDao.lista().forEach(cliente -> {
                System.out.println("\tID do Cliente = " + cliente.getId() + ", Nome do Cliente: " + cliente.getNome());
            });

            System.out.println("\n\nDigite o ID do Cliente: ");
            int idCliente = teclado.nextInt();

            videoDao = new VideoDao(conexao);
            System.out.println("\n\nVídeos disponíveis: \n");
            videoDao.listaVideosDisponiveis().forEach(video -> {
                System.out.println("\t" + video);
            });

            List<Video> videosParaLocacao = new ArrayList<>();
            final Cliente cliente = clienteDao.recupera(idCliente);

            while (true) {
                System.out.println("Digite o ID Do vídeo para emprestimo: ");
                int idVideo = teclado.nextInt();

                Video video = videoDao.recupera(idVideo);
                videosParaLocacao.add(video);

                System.out.println("Locar mais vídeos? [S/n]");
                String continua = teclado.next();
                if (continua.toUpperCase().equals("N")) {
                    break;
                }
            }

            emprestimoDao = new EmprestimoDao(conexao);
            emprestimoDao.realizaLocacao(cliente, videosParaLocacao);

            System.out.println("Emprestimo Realizado com sucesso");
        }
    }

}
