package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ClienteRepository {

    private EntityManager entityManager;

    public ClienteRepository(EntityManager em) {
        this.entityManager = em;
    }

    public void salva(Cliente cliente) {
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(cliente);
        transaction.commit();
    }

    public Cliente buscaPorId(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    public void atualiza(Cliente cliente) {
        entityManager.merge(cliente);
    }

    public void exclui(Cliente cliente) {
        entityManager.remove(cliente);
    }

}
