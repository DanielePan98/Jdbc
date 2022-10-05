package org.jdbc.Dao;

import java.sql.Connection;
import java.util.List;

public interface UtenteDao {

    void createUtente(Utente utente, Connection con);

    List<Utente> getAllUtente(Connection con);

    void deleteUtente(int id, Connection con);

    Utente getUtenteById(int id, Connection con);

    void updateUtente(int id, Utente utente, Connection con);

    void createTable(Connection con);

}
