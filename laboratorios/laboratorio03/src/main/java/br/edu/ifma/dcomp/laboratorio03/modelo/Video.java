package br.edu.ifma.dcomp.laboratorio03.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;

final public class Video {

    final public static int DISPOSNIVEL = 1;
    final public static int LOCADO = 2;

    private int id;
    private int status;
    private String tipo;
    private BigDecimal valorDaDiaria;

    private Filme filme;
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public Video(Integer status, String tipo, BigDecimal valorDaDiaria) {
        this.status = status;
        this.tipo = tipo;
        this.valorDaDiaria = valorDaDiaria;
    }

    public Video(Integer status, String tipo, BigDecimal valorDaDiaria, Filme filme) {
        this(status, tipo, valorDaDiaria);
        this.filme = filme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValorDaDiaria() {
        return valorDaDiaria;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return filme;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adiciona(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    @Override
    public String toString() {
        return "Video {" +
                "id=" + id +
                ", status=" + status +
                ", tipo='" + tipo + '\'' +
                ", valorDaDiaria=" + valorDaDiaria +
                ", filme=" + filme +
                '}';
    }
}
