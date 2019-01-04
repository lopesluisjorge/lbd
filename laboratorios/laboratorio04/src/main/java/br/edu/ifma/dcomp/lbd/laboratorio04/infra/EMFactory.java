package br.edu.ifma.dcomp.lbd.laboratorio04.infra;

import org.hibernate.boot.model.relational.Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFactory {

    final private EntityManagerFactory factory = Persistence.createEntityManagerFactory("laboratorio04");

    public EntityManager getEntityManager() {
        return factory.createEntityManager();

    }

    public void close() {
        factory.close();
    }

}
