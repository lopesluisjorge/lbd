package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.StatusEmprestimo;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.EmprestimoRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class DevolucaoService {

    public EntityManager entityManager;

    private EmprestimoRepository emprestimoRepository;

    public DevolucaoService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.emprestimoRepository = new EmprestimoRepository(entityManager);
    }

    public void devolve(Emprestimo emprestimo) {
        emprestimo.setDataDeDevolucao(LocalDate.now());
        emprestimo.calculaValorDoAluguel();
        emprestimoRepository.atualiza(emprestimo);
    }

    public void paga(Emprestimo emprestimo) {
        emprestimo.setStatus(StatusEmprestimo.PAGO);
        emprestimoRepository.atualiza(emprestimo);
    }

}
