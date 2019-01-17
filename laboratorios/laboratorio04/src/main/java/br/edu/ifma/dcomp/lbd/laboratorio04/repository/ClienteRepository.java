package br.edu.ifma.dcomp.lbd.laboratorio04.repository;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Cliente;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Emprestimo;
import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteRepository {

    private EntityManager entityManager;

    public ClienteRepository(EntityManager em) {
        this.entityManager = em;
    }

    public void salva(Cliente cliente) {
        entityManager.persist(cliente);
    }

    public Cliente buscaPorId(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    public List<Cliente> buscaPorNome(String nome) {
        return entityManager
                .createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
                .setParameter("nome", nome.toUpperCase()+"%")
                .getResultList();
    }

    public Cliente buscaPorCpf(String cpf) {
        return entityManager
                .createQuery("from Cliente where cpf = :cpf", Cliente.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
    }

    /**
     * TODO
     * @return
     */
    public List<Cliente> comDebitoEmAtraso() {
        String jpql = "from Cliente c join Empresimo e where e.";

        return null;
    }

    /**
     * TODO
     * @param cliente
     * @return
     */
    public List<Emprestimo> emprestimosEmAtraso(Cliente cliente) {
        String jpql = "from Cliente c inner join fetch c.emprestimos e where e.";

        return null;
    }

    public void atualiza(Cliente cliente) {
        entityManager.merge(cliente);
    }

    public void exclui(Cliente cliente) {
        entityManager.remove(cliente);
    }

}
