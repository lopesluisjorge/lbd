package edu.ifma.dcomp.laboratorio03.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Emprestimo {

    private Integer id;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private BigDecimal valorAluguel;
    private Boolean status;

    private Cliente cliente;

    public Emprestimo(LocalDate dataLocacao, LocalDate dataDevolucao, BigDecimal valorAluguel, Boolean status) {
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valorAluguel = valorAluguel;
        this.status = status;
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
}
