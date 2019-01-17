package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import org.hibernate.TransientPropertyValueException;

import javax.persistence.EntityManager;
import java.util.List;

public class VideoRepository {

    private EntityManager entityManager;

    public VideoRepository(EntityManager em) {
        this.entityManager = em;
    }

    public void salva(Video video) {
        try {
            entityManager.persist(video);
        } catch (TransientPropertyValueException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Video> lista() {
        return entityManager
                .createQuery("from Video", Video.class)
                .getResultList();
    }

    public List<Video> pagina(Integer inicio, Integer quantidade) {
        return entityManager
                .createQuery("from Video limit :inicio, :fim", Video.class)
                .setFirstResult(inicio)
                .setMaxResults(quantidade)
                .getResultList();
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
