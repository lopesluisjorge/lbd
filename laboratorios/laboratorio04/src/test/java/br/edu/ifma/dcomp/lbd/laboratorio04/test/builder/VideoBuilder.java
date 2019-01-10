package br.edu.ifma.dcomp.lbd.laboratorio04.test.builder;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;

import java.math.BigDecimal;

public class VideoBuilder {

    final private Video video = new Video();

    public VideoBuilder comFilme(Filme filme) {
        video.setFilme(filme);
        return this;
    }

    public VideoBuilder comStatus(Integer status) {
        video.setStatus(status);
        return this;
    }

    public VideoBuilder comTipo(String tipo) {
        video.setTipo(tipo);
        return this;
    }

    public VideoBuilder comValorDaDiaria(BigDecimal diaria) {
        video.setValorDaDiaria(diaria);
        return this;
    }

    public Video build() {
        if (null == video.getFilme()) video.setFilme(new FilmeBuilder().build());
        if (null == video.getStatus()) video.setStatus(1);
        if (null == video.getTipo()) video.setTipo("DVD");
        if (null == video.getValorDaDiaria()) video.setValorDaDiaria(new BigDecimal(1.99));

        return video;
    }

}
