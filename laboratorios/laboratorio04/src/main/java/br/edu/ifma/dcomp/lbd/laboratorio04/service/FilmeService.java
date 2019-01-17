package br.edu.ifma.dcomp.lbd.laboratorio04.service;

import br.edu.ifma.dcomp.lbd.laboratorio04.model.Filme;
import br.edu.ifma.dcomp.lbd.laboratorio04.repository.FilmeRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class FilmeService {

    private EntityManager entityManager;
    private FilmeRepository filmeRepository;

    public FilmeService(EntityManager em) {
        this.entityManager = em;
        this.filmeRepository = new FilmeRepository(em);
    }

    public Filme adiciona(Filme filme) {
        entityManager.getTransaction().begin();
        filmeRepository.salva(filme);
        entityManager.getTransaction().commit();

        return filme;
    }

    public void remove(Filme filme) {
        filmeRepository.exclui(filme);
    }

    public List<Filme> porTitulo(String titulo) {
        return filmeRepository.buscaPorTitulo(titulo);
    }

    public List<Filme> porGenero(String genero) {
        return filmeRepository.buscaPorGenero(genero);
    }

    public Filme porId(Integer id) {
        return filmeRepository.buscaPorId(id);
    }

}
