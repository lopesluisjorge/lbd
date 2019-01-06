package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;

import javax.persistence.EntityManager;

public class FilmeRepository {

    private EntityManager entityManager;

    public FilmeRepository(EntityManager em) {
        this.entityManager = em;
    }

    public void salva(Filme filme) {
        entityManager.persist(filme);
    }

    public Filme buscaPorId(Integer id) {
        return entityManager.find(Filme.class, id);
    }

    public void atualiza(Filme filme) {
        entityManager.merge(filme);
    }

    public void exclui(Filme filme) {
        entityManager.remove(filme);
    }

}
