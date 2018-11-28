package br.edu.ifma.dcomp.laboratorio03.modelo;

import java.util.ArrayList;

final public class Filme {

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Integer anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void adicionar(Video video) {
        videos.add(video);
    }

}
