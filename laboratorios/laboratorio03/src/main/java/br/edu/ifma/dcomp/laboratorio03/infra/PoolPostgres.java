package br.edu.ifma.dcomp.laboratorio03.infra;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

final public class PoolPostgres extends PoolDeConexoes {

    private DataSource dataSource;

    public PoolPostgres() {
        PGSimpleDataSource pgDataSource = new PGSimpleDataSource();

        pgDataSource.setServerName("localhost");
        pgDataSource.setPortNumber(5432);
        pgDataSource.setDatabaseName("locadora_db");
        pgDataSource.setUser("postgres");
        pgDataSource.setPassword("");
        pgDataSource.setSsl(false);

        this.dataSource = pgDataSource;
    }

    public Connection getConexao() throws SQLException {
        return dataSource.getConnection();
    }

}
