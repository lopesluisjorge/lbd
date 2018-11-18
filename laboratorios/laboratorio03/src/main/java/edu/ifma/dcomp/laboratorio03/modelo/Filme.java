package edu.ifma.dcomp.laboratorio03.modelo;

import java.util.ArrayList;
import java.util.List;

final public class Filme {

    private Integer id;
    private String titulo;
    private Integer anoDeLancamento;
    private Integer duracao;
    private String genero;

    final private ArrayList<Video> videos = new ArrayList<>();

    public Filme(String titulo, Integer anoDeLancamento, Integer duracao, String genero) {
        this.titulo = titulo;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public String getGenero() {
        return genero;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void adicionar(Video video) {
        videos.add(video);
    }

}
