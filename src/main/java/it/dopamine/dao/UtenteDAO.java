package it.dopamine.dao;

import it.dopamine.model.Utente;
import it.dopamine.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

public class UtenteDAO {
    public int registraUtente(Utente utente) {
        String INSERT_USER_SQL = "INSERT INTO utenti" +
                " (admin, nome, cognome, indirizzo, telefono, email, password, metodo_pagamento, carta_ultime_4_cifre, carta_scadenza) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setString(8, utente.getMetodo_pagamento());
            ps.setString(9, utente.getCarta_ultime_4_cifre());
            ps.setString(10, utente.getScadenza_carta());

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
            		u.setMetodo_pagamento(rs.getString("metodo_pagamento"));
            		u.setCarta_ultime_4_cifre(rs.getString("carta_ultime_4_cifre"));
            		u.setScadenza_carta(rs.getString("carta_scadenza"));
            	}
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
	            		u.setMetodo_pagamento(rs.getString("metodo_pagamento"));
	            		u.setCarta_ultime_4_cifre(rs.getString("carta_ultime_4_cifre"));
	            		u.setScadenza_carta(rs.getString("carta_scadenza"));
    				}
    			}
    		}
    		
    		
    		
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return u;
    }

    public boolean cambiaPassword(String email, String oldPassword, String newPassword) {
        final String TAKE_USER = "SELECT password FROM utenti WHERE email = ?";

        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(TAKE_USER)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String passwordSalvata = rs.getString("password");

                    if (BCrypt.checkpw(oldPassword, passwordSalvata)) {
                        String updateSql = "UPDATE utenti SET password = ? WHERE email = ?";
                        String nuovoHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());

                        try (PreparedStatement psUpdate = connessione.prepareStatement(updateSql)) {
                            psUpdate.setString(1, nuovoHash);
                            psUpdate.setString(2, email);
                            psUpdate.executeUpdate();
                            return true;
                        }

                    } else {
                        return false;
                    }

                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean changeAddress(String address, int id) {
        final String UPDATE_SQL = "UPDATE utenti SET indirizzo = ? WHERE id_utente = ?";

        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, address);
            ps.setInt(2, id);

            int righeAggiornate = ps.executeUpdate();
            return righeAggiornate > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePaymentMethod(String metodoPagamento, String ultimeCifreCarta, String scadenzaCarta, int id) {

        final String UPDATE_SQL = 
                "UPDATE utenti SET metodo_pagamento = ?, carta_ultime_4_cifre = ?, carta_scadenza = ? WHERE id_utente = ?";

        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(UPDATE_SQL)) {


            ps.setString(1, metodoPagamento);
            ps.setString(2, ultimeCifreCarta);
            ps.setString(3, scadenzaCarta);
            ps.setInt(4, id);


            int righeAggiornate = ps.executeUpdate();

            return righeAggiornate > 0;


        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}