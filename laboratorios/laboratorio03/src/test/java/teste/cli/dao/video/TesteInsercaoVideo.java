package teste.cli.dao.video;

import br.edu.ifma.dcomp.laboratorio03.dao.FilmeDao;
import br.edu.ifma.dcomp.laboratorio03.dao.VideoDao;
import br.edu.ifma.dcomp.laboratorio03.infra.PoolPostgres;
import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;
import br.edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Scanner;

final public class TesteInsercaoVideo {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        try (Connection conexao = new PoolPostgres().getConexao()) {
            VideoDao videoDao = new VideoDao(conexao);

            System.out.println("Digite o ID do filme: ");
            int idFilme = teclado.nextInt();

            FilmeDao filmeDao = new FilmeDao(conexao);

            Filme filme = filmeDao.recupera(idFilme);

            System.out.println("Tipo (DVD ou VHS): ");
            teclado.next();
            String tipo = teclado.nextLine();

            System.out.println("Valor da Diária: ");
            BigDecimal valorDiaria = teclado.nextBigDecimal();

            Video video = new Video(Video.DISPOSNIVEL, tipo, valorDiaria);
            video.setFilme(filme);

            videoDao.salva(video);

            System.out.println("ID: " + video.getId());
        }
    }

}
