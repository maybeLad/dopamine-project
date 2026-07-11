package it.dopamine.dao;

import it.dopamine.model.Utente;
import it.dopamine.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDAO {
    public static int registraUtente(Utente utente) throws ClassNotFoundException {
        String INSERT_USER_SQL = "INSERT INTO utente" +
                " (admin, nome, cognome, indirizzo, telefono, email, password) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?)";

        int ris = 0;
        
        try (Connection connessione = Connector.getConnection();
        		PreparedStatement ps = connessione.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)
        	) {

            ps.setBoolean(1, utente.isAdmin());
            ps.setString(2, utente.getNome());
            ps.setString(3, utente.getCognome());
            ps.setString(4, utente.getIndirizzo());
            ps.setString(5, utente.getTelefono());
            ps.setString(6, utente.getEmail());
            ps.setString(7, utente.getPassword());

            ris = ps.executeUpdate();

        } catch (SQLException e) {
            e.getMessage();
        }


        return ris;
    }
    
    public static Utente getUtente(int id) {
    	Utente u = null;
    	
    	String SEARCH_USER = "SELECT * FROM utenti WHERE id_utente = ?";
        
    	try (Connection connessione = Connector.getConnection();
                PreparedStatement ps = connessione.prepareStatement(SEARCH_USER, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
            	if(rs.next()) {
            		u = new Utente();
            		
            		u.setId(id);
            		u.setNome(rs.getString("nome"));
            		u.setCognome(rs.getString("cognome"));
            		u.setEmail(rs.getString("email"));
            		u.setIndirizzo(rs.getString("indirizzo"));
            		u.setTelefono(rs.getString("telefono"));
            	}
            }

        } catch (SQLException e) {
            e.getMessage();
        }


        return u;
    } 
}