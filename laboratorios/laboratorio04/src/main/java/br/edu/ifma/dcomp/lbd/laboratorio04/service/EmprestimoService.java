package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.StatusVideo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.ClienteRepository;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.EmprestimoRepository;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public class EmprestimoService {

    private EntityManager entityManager;
    private Cliente cliente;

    private DebitoService debitoService;

    private EmprestimoRepository emprestimoRepository;
    private ClienteRepository clienteRepository;

    public EmprestimoService(EntityManager em, Cliente cliente) {
        this.entityManager = em;
        this.cliente = cliente;

        this.debitoService = new DebitoService(entityManager);

        this.emprestimoRepository = new EmprestimoRepository(entityManager, cliente);
        this.clienteRepository = new ClienteRepository(entityManager);
    }

    /**
     * TODO
     * @param videos
     */
    public void aloca(Video ... videos) {
        final List<Video> videoList = Arrays.asList(videos);

        if (debitoService.locacoesEmAtraso(cliente) != null) {
            throw new RuntimeException("Não pode alocar vídeo pois tem emprestimos em atraso");
        }

        entityManager.getTransaction().begin();

        final Emprestimo emprestimo = new Emprestimo();
        emprestimo.adiciona(videos);
        cliente.adicionar(emprestimo);
        videoList.forEach(video -> video.setStatus(StatusVideo.EMPRESTADO));

        emprestimoRepository.atualiza(emprestimo);
        clienteRepository.atualiza(cliente);

        entityManager.flush();

        entityManager.getTransaction().commit();
    }

    private boolean isDisponivel(Video video) {
        return video.getStatus().equals(StatusVideo.DISPONIVEL);
    }

}
