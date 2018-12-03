package br.edu.ifma.dcomp.laboratorio03.construcao;

import br.edu.ifma.dcomp.laboratorio03.modelo.Video;

import java.math.BigDecimal;

final public class ConstrutorVideo {

    public static Video constroi(int status, String tipo, BigDecimal valorDiaria) {
        return new Video(status, tipo, valorDiaria);
    }

    public static Video constroi(int status, String tipo, BigDecimal valorDiaria, int id) {
        final Video video = constroi(status, tipo, valorDiaria);
        video.setId(id);

        return video;
    }


}
