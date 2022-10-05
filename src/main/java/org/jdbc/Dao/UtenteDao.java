package org.jdbc.Dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface UtenteDao {

    void createUtente(Utente utente,Connection con);

    List<Utente> getAllUtente(Connection con);

    void deleteUtente(int id,Connection con);

    Optional<Utente> findUtenteById(int id,Connection con);

    void updateUtente(Utente utente,Connection con);

    void createTable(Connection con);

}
