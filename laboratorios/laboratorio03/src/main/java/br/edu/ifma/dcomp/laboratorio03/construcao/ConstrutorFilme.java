package br.edu.ifma.dcomp.laboratorio03.construcao;

import br.edu.ifma.dcomp.laboratorio03.modelo.Filme;

final public class ConstrutorFilme {

    public static Filme constroi(String titulo, int anoDeLancamento, int duracao, String genero) {
        return new Filme(titulo, anoDeLancamento, duracao, genero);
    }

    public static Filme constroi(String titulo, int anoDeLancamento, int duracao, String genero, int id) {
        final Filme filme = constroi(titulo, anoDeLancamento, duracao, genero);
        filme.setId(id);

        return filme;
    }

}
