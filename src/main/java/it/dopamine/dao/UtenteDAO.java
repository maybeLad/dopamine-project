package it.dopamine.dao;

import it.dopamine.model.Utente;
import it.dopamine.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.cj.xdevapi.PreparableStatement;

public class UtenteDAO {
    public int registraUtente(Utente utente) {
        String INSERT_USER_SQL = "INSERT INTO utenti" +
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
            e.printStackTrace();
        }


        return ris;
    }
    
    public Utente getUtente(int id) {
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
            		u.setPassword(rs.getString("password"));
            	}
            }

        } catch (SQLException e) {
            e.getMessage();
        }


        return u;
    } 
    
    public Utente checkLogin(String email, String password) {
    	String CHECK_USER = "SELECT * FROM utenti WHERE email = ?";
    	Utente u = null;
    	
    	try(Connection connessione = Connector.getConnection();
    			PreparedStatement ps = connessione.prepareStatement(CHECK_USER)){
    		
    		ps.setString(1, email);
    		
    		try(ResultSet rs = ps.executeQuery()){
    			if(rs.next()) {
    				
    				String hashPassword = rs.getString("password");
    				if(BCrypt.checkpw(password, hashPassword)) {
	    				u = new Utente();
	    				
	    				u.setId(rs.getInt("id_utente"));
	            		u.setNome(rs.getString("nome"));
	            		u.setCognome(rs.getString("cognome"));
	            		u.setEmail(email);
	            		u.setIndirizzo(rs.getString("indirizzo"));
	            		u.setTelefono(rs.getString("telefono"));
    				}
    			}
    		}
    		
    		
    		
    	}catch (SQLException e) {
    		e.getMessage();
    	}
    	
    	return u;
    }
    
}