package br.edu.ifma.dcomp.laboratorio03.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

final public class Emprestimo {

    final public static boolean ATIVO = true;
    final public static boolean DEVOLVIDO = false;

    private int id;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private BigDecimal valorAluguel;
    private boolean status;

    private Cliente cliente;
    private ArrayList<Video> videos = new ArrayList<>();

    public Emprestimo(LocalDate dataLocacao, LocalDate dataDevolucao, BigDecimal valorAluguel, boolean status) {
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valorAluguel = valorAluguel;
        this.status = status;
    }

    public Emprestimo(
            Cliente cliente, LocalDate dataLocacao, LocalDate dataDevolucao, BigDecimal valorAluguel, boolean status) {
        this(dataLocacao, dataDevolucao, valorAluguel, status);
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", dataLocacao=" + dataLocacao +
                ", dataDevolucao=" + dataDevolucao +
                ", valorAluguel=" + valorAluguel +
                ", status=" + status +
                ", cliente=" + cliente +
                '}';
    }
}
