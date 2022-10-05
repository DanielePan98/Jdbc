package org.jdbc;

import org.jdbc.Dao.Utente;
import org.jdbc.Dao.UtenteDao;
import org.jdbc.Dao.UtenteDaoImpl;
import org.jdbc.Utils.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class Application {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        UtenteDao utenteDao = new UtenteDaoImpl();
        Connection con = ConnectionFactory.getConnection();
        utenteDao.createTable(con);
        utenteDao.createUtente(new Utente("Giuseppe", "Verdi"), con);
        ConnectionFactory.close(con);

    }
}