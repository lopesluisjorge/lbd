package br.edu.ifma.dcomp.lbd.laboratorio04.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    final public static int MAXIMO_DIAS_DO_EMPRESTIMO = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "data_locacao")
    private LocalDate dataDeLocacao = LocalDate.now();


    @Column(name = "data_devolucao")
    private LocalDate dataDeDevolucao;


    @Column(name = "valor_aluguel")
    private BigDecimal valorDoAluguel;


    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status = StatusEmprestimo.ATIVO;


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


    public Integer getId() {
        return id;
    }

    public LocalDate getDataDeLocacao() {
        return dataDeLocacao;
    }

    public void setDataDeLocacao(LocalDate dataDeLocacao) {
        this.dataDeLocacao = dataDeLocacao;
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

    public void calculaValorDoAluguel() {
        if (dataDeDevolucao == null ) {
            throw new RuntimeException("Não é possivel calcular o valor do aluguel");
        }

        int dias;
        for (dias = 0; dataDeDevolucao.equals(dataDeLocacao.plusDays(dias)); dias++);

        BigDecimal valorTotalDiaria = new BigDecimal(0);
        videos.forEach(video -> {
            valorTotalDiaria.add(video.getValorDaDiaria());
        });

        this.valorDoAluguel = valorTotalDiaria.plus(new MathContext(dias));
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
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

    public void adiciona(List<Video> videos) {
        this.videos.addAll(videos);
    }

    public Set<Video> getVideos() {
        return videos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
