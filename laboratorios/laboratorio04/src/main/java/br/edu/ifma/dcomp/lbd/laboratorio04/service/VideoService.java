package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.VideoRepository;

import javax.persistence.EntityManager;

public class VideoService {

    private EntityManager entityManager;
    private VideoRepository videoRepository;

    public VideoService(EntityManager em) {
        this.entityManager = em;
        this.videoRepository = new VideoRepository(em);
    }

    public Video adiciona(Video video) {
        entityManager.getTransaction().begin();
        videoRepository.salva(video);
        entityManager.getTransaction().commit();

        return video;
    }

}
