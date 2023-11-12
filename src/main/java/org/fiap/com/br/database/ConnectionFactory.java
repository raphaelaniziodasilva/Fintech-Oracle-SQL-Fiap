package org.fiap.com.br.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USERNAME = "rm550954";
    private static final String PASSWORD = "081194";

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do Oracle não encontrado", e);
        }
    }

    private ConnectionFactory() {}

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Banco de dados conectado");
            return connection;
        } catch (SQLException ex) {
            System.err.println("Error ao se conectar ao banco de dados: " + ex.getMessage());
            throw new RuntimeException("Error ao se conectar ao banco de dados", ex);
        }
    }
}
