package br.edu.ifma.dcomp.lbd.laboratorio04.model;

public enum Categoria {
    LANCAMENTO("Lancamento"),
    CATALOGO("Catalogo"),
    PROMOCAO("Promocao");

    private String categoria;

    private Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String get() {
        return categoria;
    }
}
