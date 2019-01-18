package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;

import javax.persistence.EntityManager;

public class EmprestimoRepository {

    private EntityManager entityManager;

    public EmprestimoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void atualiza(Emprestimo emprestimo) {
        entityManager.merge(emprestimo);
    }

}
