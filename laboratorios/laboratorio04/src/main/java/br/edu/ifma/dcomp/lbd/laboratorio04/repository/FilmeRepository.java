package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import javax.persistence.EntityManager;
import java.util.List;

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

    public List<Filme> buscaPorTitulo(String titulo) {
        return entityManager
                .createQuery("from Filme where upper(titulo) like :titulo", Filme.class)
                .setParameter("titulo", titulo.toUpperCase()+"%")
                .getResultList();
    }

    public List<Filme> buscaPorGenero(String genero) {
        return entityManager
                .createQuery("from Filme where upper(genero) = :genero", Filme.class)
                .setParameter("genero", genero.toUpperCase())
                .getResultList();
    }

    public void atualiza(Filme filme) {
        entityManager.merge(filme);
    }

    public void exclui(Filme filme) {
        try {
            entityManager.remove(filme);
        } catch (Exception e) { // SqlExceptionHelper
            throw new RuntimeException(e);
        }
    }

}
