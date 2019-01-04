package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "data_locacao")
    private LocalDate dataDeLocacao;


    @Column(name = "data_devolucao")
    private LocalDate dataDeDevolucao;


    @Column(name = "valor_aluguel")
    private BigDecimal valorDoAluguel;


    private Boolean status;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @ManyToMany
    @JoinTable(
            name = "emprestimo_video",
            joinColumns = @JoinColumn(name = "emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> videos = new HashSet<>();


    @PrePersist
    private void prePersist() {
        dataDeLocacao = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDataDeLocacao() {
        return dataDeLocacao;
    }

    public LocalDate getDataDeDevolucao() {
        return dataDeDevolucao;
    }

    public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
        this.dataDeDevolucao = dataDeDevolucao;
    }

    public BigDecimal getValorDoAluguel() {
        return valorDoAluguel;
    }

    public void setValorDoAluguel(BigDecimal valorDoAluguel) {
        this.valorDoAluguel = valorDoAluguel;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adiciona(Video ... videos) {
        this.videos.addAll(Arrays.asList(videos));
    }

    public Set<Video> getVideos() {
        return videos;
    }

}
