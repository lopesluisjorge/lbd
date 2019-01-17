package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;

import javax.persistence.EntityManager;

public class EmprestimoRepository {

    private EntityManager entityManager;
    private Cliente cliente;

    public EmprestimoRepository(EntityManager entityManager, Cliente cliente) {
        this.entityManager = entityManager;
        this.cliente = cliente;
    }

    public void atualiza(Emprestimo emprestimo) {
        entityManager.merge(emprestimo);
    }

    public void devolve() {

    }

}
