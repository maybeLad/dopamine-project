package it.dopamine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.dopamine.model.Prodotto;
import it.dopamine.model.Recensione;
import it.dopamine.util.Connector;

public class ReviewDAO {
	public ArrayList<Recensione> getReviews(){
		ArrayList<Recensione> arr = new ArrayList<Recensione>();
		
		final String TAKE_ALL = "SELECT * FROM recensioni ORDER BY data_recensione DESC LIMIT 9";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connessione = Connector.getConnection();
				 PreparedStatement ps = connessione.prepareStatement(TAKE_ALL, Statement.RETURN_GENERATED_KEYS);
				 ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Recensione r = new Recensione();
					
					r.setId(rs.getInt("id_recensione"));
					r.setId_utente(rs.getInt("id_utente"));
					r.setId_prodotto(rs.getInt("id_prodotto"));
					r.setVoto(rs.getInt("voto"));
					r.setDescrizione(rs.getString("descrizione"));
					
					arr.add(r);
				}	

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public boolean insertReview(int idUser, int idProduct, int voto, String description) {      
	    final String INSERT_REVIEW = "INSERT INTO recensioni (id_utente, id_prodotto, voto, descrizione) VALUES (?, ?, ?, ?)";

	    try (Connection conn = Connector.getConnection(); 
	         PreparedStatement pstmt = conn.prepareStatement(INSERT_REVIEW)) {

	        pstmt.setInt(1, idUser);
	        pstmt.setInt(2, idProduct);
	        pstmt.setInt(3, voto);
	        pstmt.setString(4, description);

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
		