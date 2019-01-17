package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;

import javax.persistence.EntityManager;

public class EmprestimoService {

    private EntityManager entityManager;
    private Cliente cliente;
    private DebitoService debitoService;

    public EmprestimoService(EntityManager em, Cliente cliente) {
        this.entityManager = em;
        this.cliente = cliente;
    }

    /**
     * TODO
     * @param video
     */
    public void aloca(Video video) {

    }

    private boolean isDisponivel(Video video) {
        return video.getStatus() == 0;
    }

}
