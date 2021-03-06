package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.ClienteRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteService {

    private EntityManager entityManager;
    private ClienteRepository clienteRepository;

    public ClienteService(EntityManager entityManager) {
        this.entityManager = entityManager;
        clienteRepository = new ClienteRepository(entityManager);
    }

    public Cliente adiciona(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.buscaPorCpf(cliente.getCpf());

        if (clienteExistente != null) {
            throw new RuntimeException("Cliente já está cadastrado");
        }

        entityManager.getTransaction().begin();
        clienteRepository.salva(cliente);
        entityManager.getTransaction().commit();

        return cliente;
    }

    public List<Cliente> porNome(String nome) {
        return clienteRepository.buscaPorNome(nome);
    }

    public Cliente porCpf(String cpf) {
        return clienteRepository.buscaPorCpf(cpf);
    }

    /**
     * @throws RuntimeException
     * @param cliente
     */
    public void remove(Cliente cliente) {
        if (clienteRepository.quantidadeDeEmprestimosEmAtraso(cliente) == 0) {
            throw new RuntimeException("Você está com empréstimos em atraso.");
        }

        entityManager.getTransaction().begin();

        cliente.desativar();
        clienteRepository.atualiza(cliente);

        entityManager.getTransaction().commit();
    }

}
