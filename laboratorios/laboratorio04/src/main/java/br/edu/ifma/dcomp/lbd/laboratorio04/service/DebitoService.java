package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.ClienteRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DebitoService {

    private EntityManager entityManager;
    private ClienteRepository clienteRepository;

    public DebitoService(EntityManager em) {
        this.entityManager = em;
        clienteRepository = new ClienteRepository(entityManager);
    }

    public List<Cliente> emAtraso() {
        return clienteRepository.comDebitoEmAtraso();
    }

    public List<Emprestimo> locacoesEmAtraso(Cliente cliente) {
        return clienteRepository.emprestimosEmAtraso(cliente);
    }

}
