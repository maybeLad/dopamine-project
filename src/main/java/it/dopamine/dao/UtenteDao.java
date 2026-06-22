package it.dopamine.dao;

import it.dopamine.model.Utente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDao {
    public int registraUtente(Utente utente) throws ClassNotFoundException {
        String INSERT_USER_SQL = "INSERT INTO utente" +
                " (admin, nome, cognome, indirizzo, telefono, email, password) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?)";

        int ris = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connessione = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dopamine?useSSL=false&serverTimezone=UTC",
                    "root", "ciccio4913");
                    PreparedStatement ps = connessione.prepareStatement(INSERT_USER_SQL,
                            Statement.RETURN_GENERATED_KEYS)) {

                ps.setBoolean(1, utente.isAdmin());
                ps.setString(2, utente.getNome());
                ps.setString(3, utente.getCognome());
                ps.setString(4, utente.getIndirizzo());
                ps.setString(5, utente.getTelefono());
                ps.setString(6, utente.getEmail());
                ps.setString(7, utente.getPassword());

                ris = ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return ris;
    }
}