package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.StatusVideo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Video;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.ClienteRepository;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.EmprestimoRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmprestimoService {

    private EntityManager entityManager;

    private EmprestimoRepository emprestimoRepository;
    private ClienteRepository clienteRepository;

    public EmprestimoService(EntityManager em) {
        this.entityManager = em;

        this.emprestimoRepository = new EmprestimoRepository(entityManager);
        this.clienteRepository = new ClienteRepository(entityManager);
    }



    public Emprestimo aloca(Cliente cliente, LocalDate dataLocacao, Video ... videos) {

        if (!cliente.isAtivo()) {
            throw new RuntimeException("Cliente removido não pode registrar empréstimo");
        }

        if (clienteRepository.quantidadeDeEmprestimosEmAtraso(cliente) != 0) {
            throw new RuntimeException("Não pode alocar vídeo pois tem emprestimos em atraso");
        }

        final List<Video> videosDisponiveis = new ArrayList<>();
        Arrays.asList(videos).forEach(video -> {
            if (isDisponivel(video)) {
                videosDisponiveis.add(video);
            }
        });

        entityManager.getTransaction().begin();

        final Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataDeLocacao(dataLocacao);
        emprestimo.adiciona(videosDisponiveis);
        emprestimo.setCliente(cliente);
        cliente.adicionar(emprestimo);
        videosDisponiveis.forEach(video -> video.setStatus(StatusVideo.EMPRESTADO));

        emprestimoRepository.atualiza(emprestimo);
        clienteRepository.atualiza(cliente);

        entityManager.getTransaction().commit();

        return emprestimo;
    }

    public Emprestimo aloca(Cliente cliente, Video ... videos) {
        return aloca(cliente, LocalDate.now(), videos);
    }

    private boolean isDisponivel(Video video) {
        return video.getStatus().equals(StatusVideo.DISPONIVEL);
    }

}
