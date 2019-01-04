package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String titulo;


    @Column(name = "ano_lancamento")
    private Integer anoDeLancamento;


    private Double duracao;


    @Column(nullable = false)
    private String genero;


    @OneToMany(mappedBy = "filme")
    final private List<Video> videos = new ArrayList<>();


    public Integer getId() {
        return id;
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

    public Double getDuracao() {
        return duracao;
    }

    public void setDuracao(Double duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void adicionar(Video ... videos) {
        this.videos.addAll(Arrays.asList(videos));
    }

    public List<Video> getVideos() {
        return videos;
    }
}
