package org.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.jdbc.Dao.Utente;
import org.jdbc.Dao.UtenteDao;
import org.jdbc.Dao.UtenteDaoImpl;
import org.jdbc.Utils.ConnectionFactory;

import java.sql.Connection;

@Slf4j
public class Application {

    public static void main(String[] args) {

        UtenteDao utenteDao = new UtenteDaoImpl();
        Connection con = ConnectionFactory.getConnection();
        utenteDao.createTable(con);
        utenteDao.createUtente(new Utente("Giuseppe", "Verdi"), con);
        System.out.println(utenteDao.getAllUtente(con));
        utenteDao.updateUtente(2, new Utente("Marco", "Merino"), con);
        System.out.println(utenteDao.getUtenteById(2, con));
        utenteDao.deleteUtente(2, con);
        System.out.println(utenteDao.getUtenteById(2, con));
        ConnectionFactory.close(con);

    }
}