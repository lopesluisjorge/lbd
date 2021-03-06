package br.edu.ifma.dcomp.lbd.laboratorio04;

import br.edu.ifma.dcomp.lbd.laboratorio04.infra.EMFactory;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.*;
import br.edu.ifma.dcomp.lbd.laboratorio04.service.ClienteService;
import br.edu.ifma.dcomp.lbd.laboratorio04.service.EmprestimoService;
import br.edu.ifma.dcomp.lbd.laboratorio04.service.FilmeService;
import br.edu.ifma.dcomp.lbd.laboratorio04.service.VideoService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class AlocaVideoTest {

    public static void main(String[] args) {
        EMFactory entityManagerFactory = new EMFactory();

        final EntityManager entityManager = entityManagerFactory.getEntityManager();

        final ClienteService clienteService = new ClienteService(entityManager);
        final FilmeService filmeService = new FilmeService(entityManager);
        final VideoService videoService = new VideoService(entityManager);

        final EnderecoCliente endereco = new EnderecoCliente();
        endereco.setCep("65000000");
        endereco.setCidade("São Luis");
        endereco.setUf("MA");
        endereco.setLogradouro("Rua A");
        endereco.setBairro("Bairro de Fárima");
        endereco.setNumero(12);

        final Cliente cliente = new Cliente();
        cliente.setNome("Jon");
        cliente.setTelefone("98988887777");
        cliente.setCpf("12345678926");
        cliente.setEndereco(endereco);

        clienteService.adiciona(cliente);

        final Filme filme = new Filme();

        filme.setTitulo("Seja o que Deus quiser");
        filme.setAnoDeLancamento(2019);
        filme.setDuracao(2);
        filme.setGenero("Drama");

        final Video video = new Video();
        video.setFilme(filme);
        video.setStatus(StatusVideo.DISPONIVEL);
        video.setTipo(TipoVideo.DVD);
        video.setValorDaDiaria(new BigDecimal(1.99));

        filmeService.adiciona(filme);
        videoService.adiciona(video);

        EmprestimoService emprestimoService = new EmprestimoService(entityManager);
        emprestimoService.aloca(cliente, video);

        System.out.println("Lista de Emprestimos: " + cliente.getEmprestimos().size());

        entityManager.close();
        entityManagerFactory.close();
    }

}
