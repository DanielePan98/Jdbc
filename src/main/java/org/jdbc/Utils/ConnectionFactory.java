package org.jdbc.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    public static final String USER = "user";
    public static final String PASS = "123456Panna@";

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionFactory.class);


    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            LOG.info("Connessione effettuata correttamente!");
        } catch (SQLException e) {
            LOG.info("Connessione non effettuata!");
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
            LOG.info("Chiusura connessione avvenuta");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}