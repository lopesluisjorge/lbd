package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(columnDefinition = "smallint")
    private Integer status;


    private String tipo;


    @Column(name = "valor_diaria")
    private BigDecimal valorDaDiaria;


    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;


    public Integer getId() {
        return id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorDaDiaria() {
        return valorDaDiaria;
    }

    public void setValorDaDiaria(BigDecimal valorDaDiaria) {
        this.valorDaDiaria = valorDaDiaria;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
