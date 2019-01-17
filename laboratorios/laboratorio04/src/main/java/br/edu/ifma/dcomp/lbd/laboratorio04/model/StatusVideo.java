package br.edu.ifma.dcomp.lbd.laboratorio04.model;

public enum StatusVideo {

    DISPONIVEL("Disponível"),
    EMPRESTADO("Emprestado");

    private String status;

    private StatusVideo(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
