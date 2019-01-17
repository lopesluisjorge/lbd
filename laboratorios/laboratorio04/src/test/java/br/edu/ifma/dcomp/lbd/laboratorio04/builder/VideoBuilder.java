package br.edu.ifma.dcomp.lbd.laboratorio04.builder;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.StatusVideo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.TipoVideo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;

import java.math.BigDecimal;

public class VideoBuilder {

    final private Video video = new Video();

    private VideoBuilder() {}

    public static VideoBuilder umVideo() {
        return new VideoBuilder();
    }

    public VideoBuilder comFilme(Filme filme) {
        video.setFilme(filme);
        return this;
    }

    public VideoBuilder comStatus(StatusVideo status) {
        video.setStatus(status);
        return this;
    }

    public VideoBuilder comTipo(TipoVideo tipo) {
        video.setTipo(tipo);
        return this;
    }

    public VideoBuilder comValorDaDiaria(BigDecimal diaria) {
        video.setValorDaDiaria(diaria);
        return this;
    }

    public Video constroi() {
        if (null == video.getFilme()) video.setFilme(FilmeBuilder.umFilme().constroi());
        if (null == video.getStatus()) video.setStatus(StatusVideo.DISPONIVEL);
        if (null == video.getTipo()) video.setTipo(TipoVideo.DVD);
        if (null == video.getValorDaDiaria()) video.setValorDaDiaria(new BigDecimal(1.99));

        return video;
    }

}
