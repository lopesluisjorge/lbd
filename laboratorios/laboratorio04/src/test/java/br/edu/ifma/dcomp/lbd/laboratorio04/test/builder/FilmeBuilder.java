package br.edu.ifma.dcomp.lbd.laboratorio04.test.builder;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;

public class FilmeBuilder {

    final private Filme filme = new Filme();

    public FilmeBuilder comTitulo(String titulo) {
        filme.setTitulo(titulo);
        return this;
    }

    public FilmeBuilder comDuracao(Integer duracao) {
        filme.setDuracao(duracao);
        return this;
    }

    public FilmeBuilder comGenero(String genero) {
        filme.setGenero(genero);
        return this;
    }

    public FilmeBuilder comAnoDeLancamento(Integer ano) {
        filme.setAnoDeLancamento(ano);
        return this;
    }

    public Filme build() {
        if (null == filme.getTitulo()) filme.setTitulo("Seja o que Deus quiser");
        if (null == filme.getDuracao()) filme.setDuracao(2);
        if (null == filme.getGenero()) filme.setGenero("Drama");
        if (null == filme.getAnoDeLancamento()) filme.setAnoDeLancamento(2019);

        return filme;
    }

}
