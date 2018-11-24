package br.edu.ifma.dcomp.laboratorio03.infra;

import java.sql.Connection;
import java.sql.SQLException;

abstract public class PoolDeConexoes {

    abstract public Connection getConexao() throws SQLException;

}
