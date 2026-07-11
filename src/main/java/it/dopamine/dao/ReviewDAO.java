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
	public static ArrayList<Recensione> getReviews(){
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
				e.getMessage();
			}
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
		
		return arr;
	}
	
}
		