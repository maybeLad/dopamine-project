package it.dopamine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.dopamine.model.Prodotto;

public class ProdottoDAO {
	public static Prodotto getProdotto(String name) {
		final String TAKE_PRODUCT = "SELECT * FROM prodotti WHERE nome = ?" ;
		Prodotto product = null;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try (Connection connessione = DriverManager.getConnection("jdbc:mysql://localhost:3306/dopamine?useSSL=false&serverTimezone=UTC", "root", "admin");
				PreparedStatement ps = connessione.prepareStatement(TAKE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
				
				ps.setString(1, name);

				try (ResultSet rs = ps.executeQuery()){
                	if(rs.next()) {
                		product = new Prodotto();
                		product.setNome(name);
                		product.setId(rs.getInt("id"));
                		product.setDescrizione(rs.getString("descrizione"));
                		product.setPrezzo(rs.getDouble("prezzo"));
                		
                		
                	}
                }
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		
		return product;
	}
	
}
