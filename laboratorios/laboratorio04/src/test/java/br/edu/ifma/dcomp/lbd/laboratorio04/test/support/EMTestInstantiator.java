package br.edu.ifma.dcomp.lbd.laboratorio04.test.support;

import br.edu.ifma.dcomp.lbd.laboratorio04.infra.EMFactory;

import javax.persistence.EntityManager;

public class EMTestInstantiator {

    final private static EMFactory emFactory = new EMFactory();

    private EMTestInstantiator() {}

    public static EntityManager getEntityManager() {
        return emFactory.getEntityManager();
    }

}
