package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class VideoRepository {

    private EntityManager entityManager;

    public VideoRepository(EntityManager em) {
        this.entityManager = em;
    }

    public void salva(Video video) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(video);
        transaction.commit();
    }

    public Video buscaPorId(Integer id) {
        return entityManager.find(Video.class, id);
    }

    public void atualiza(Video video) {
        entityManager.merge(video);
    }

    public void exclui(Video video) {
        entityManager.remove(video);
    }

}
