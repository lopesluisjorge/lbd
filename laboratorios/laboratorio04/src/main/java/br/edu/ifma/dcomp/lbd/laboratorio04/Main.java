package br.edu.ifma.dcomp.lbd.laboratorio04;

import br.edu.ifma.dcomp.lbd.laboratorio04.infra.EMFactory;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        EMFactory entityManagerFactory = new EMFactory();

        final EntityManager entityManager = entityManagerFactory.getEntityManager();

        entityManager.close();
        entityManagerFactory.close();
    }

}
