package br.edu.ifma.dcomp.laboratorio03.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

final public class Emprestimo {

    private Integer id;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private BigDecimal valorAluguel;
    private Boolean status;

    private Cliente cliente;
    private ArrayList<Video> videos = new ArrayList<>();

    public Emprestimo(LocalDate dataLocacao, LocalDate dataDevolucao, BigDecimal valorAluguel, Boolean status) {
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valorAluguel = valorAluguel;
        this.status = status;
    }

    public Emprestimo(
            Cliente cliente, LocalDate dataLocacao, LocalDate dataDevolucao, BigDecimal valorAluguel, Boolean status) {
        this(dataLocacao, dataDevolucao, valorAluguel, status);
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public Boolean getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void adiciona(Video video) {
        videos.add(video);
    }

}
