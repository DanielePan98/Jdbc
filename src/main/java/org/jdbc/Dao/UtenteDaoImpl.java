package org.jdbc.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDaoImpl implements UtenteDao {

    private static final Logger LOG = LoggerFactory.getLogger(UtenteDaoImpl.class);

    @Override
    public void createUtente(Utente utente, Connection con) {
        String insert = "INSERT INTO utente (Id,Nome,Cognome) VALUES (?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(insert)) {
            con.setAutoCommit(false);
            try {
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
        List<Utente> utenteList = new ArrayList<>();
        String get = "SELECT * FROM utente";
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(get)) {
            con.setAutoCommit(false);
            try {
                while (resultSet.next()) {
                    Utente utente = new Utente();
                    utente.setId(resultSet.getInt("Id"));
                    utente.setNome(resultSet.getString("Nome"));
                    utente.setCognome(resultSet.getString("Cognome"));
                    utenteList.add(utente);
                }
                LOG.info("Retrive avvenuto correttamente");
            } catch (SQLException e) {
                con.rollback();
                LOG.info("Errore retrieve!");
                throw e;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utenteList;
    }


    @Override
    public void deleteUtente(int id, Connection con) {
        String delete = "DELETE FROM utente WHERE id like ? ";
        try (PreparedStatement ps = con.prepareStatement(delete)) {
            con.setAutoCommit(false);
            try {
                ps.setInt(1, id);
                ps.executeUpdate();
                LOG.info("Eliminazione avvenuta correttamente");
            } catch (SQLException e) {
                con.rollback();
                LOG.info("Eliminazione non avvenuta correttamente");
                throw e;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Utente getUtenteById(int id, Connection con) {
        String find = "SELECT * FROM utente WHERE Id like ?";
        Utente utente = new Utente();
        try (PreparedStatement preparedStatement = con.prepareStatement(find)) {
            con.setAutoCommit(false);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    utente.setId(resultSet.getInt("Id"));
                    utente.setNome(resultSet.getString("Nome"));
                    utente.setCognome(resultSet.getString("Cognome"));
                }
                LOG.info("Utente trovato");
            } catch (SQLException e) {
                con.rollback();
                LOG.info("Errore GetUtenteById");
                throw e;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utente;
    }


    @Override
    public void updateUtente(int id, Utente utente, Connection con) {
        String update = "UPDATE utente SET Nome = ?, Cognome = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(update)) {
            con.setAutoCommit(false);
            try {
                ps.setInt(3, id);
                ps.setString(1, utente.getNome());
                ps.setString(2, utente.getCognome());
                ps.executeUpdate();
                LOG.info("Aggiornamento avvenuto correttamente");
            } catch (SQLException e) {
                con.rollback();
                LOG.info("Aggiornamento non avvenuto correttamente");
                throw e;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void createTable(Connection con) {
        try (Statement statement = con.createStatement()) {
            con.setAutoCommit(false);
            try {
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
