package org.jdbc.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UtenteDaoImpl implements UtenteDao {

    private static final Logger LOG = LoggerFactory.getLogger(UtenteDaoImpl.class);

    @Override
    public void createUtente(Utente utente, Connection con) {
        String insert = "INSERT INTO utente (Id,Nome,Cognome) VALUES (?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(insert)) {
            try {
                con.setAutoCommit(false);
                ps.setInt(1, utente.getId());
                ps.setString(2, utente.getNome());
                ps.setString(3, utente.getCognome());
                ps.executeUpdate();
                LOG.info("Inserimento avvenuto correttamente");
            } catch (SQLException e) {
                con.rollback();
                LOG.info("Inserimento non avvenuto correttamente");
                throw e;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Utente> getAllUtente(Connection con) {
        return null;
    }

    @Override
    public void deleteUtente(int id, Connection con) {

    }

    @Override
    public Optional<Utente> findUtenteById(int id, Connection con) {
        return Optional.empty();
    }


    @Override
    public void updateUtente(Utente utente, Connection con) {
    }


    @Override
    public void createTable(Connection con) {
        try(Statement statement = con.createStatement()) {
            try {
                con.setAutoCommit(false);
                statement.executeUpdate("Create Table If Not Exists utente (Id serial primary key, Nome varchar(20) not null, Cognome varchar(30) not null)");
                LOG.info("Creazione tabella effettuata!");
            } catch (SQLException e) {
                con.rollback();
                LOG.info("Errore creazione tabella!");
                throw e;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
