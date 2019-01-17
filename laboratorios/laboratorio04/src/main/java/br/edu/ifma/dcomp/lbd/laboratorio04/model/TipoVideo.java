package br.edu.ifma.dcomp.lbd.laboratorio04.model;

public enum TipoVideo {

    VHS("VHS"),
    DVD("DVD");

    private String tipo;

    private TipoVideo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
