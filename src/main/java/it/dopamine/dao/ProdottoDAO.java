package it.dopamine.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


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
                		product.setId(rs.getInt("id_prodotto"));
                		product.setDescrizione(rs.getString("descrizione"));
                		product.setPrezzo(rs.getDouble("prezzo"));
                		product.setUrl_img(rs.getString("url_immagine"));
                		
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
	
	public static Map<String, List<Prodotto>> getProdottiRaggruppati() {
		Map<String, List<Prodotto>> catalogo = new LinkedHashMap<>();

		final String TAKE_ALL = "SELECT p.id_prodotto, p.id_categoria, p.nome, p.descrizione, "
				+ "p.prezzo, p.stock, p.url_immagine, c.nome AS nome_categoria "
				+ "FROM prodotti p "
				+ "JOIN categorie c ON p.id_categoria = c.id_categoria "
				+ "ORDER BY c.nome, p.id_prodotto";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection connessione = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dopamine?useSSL=false&serverTimezone=UTC", "root", "admin");
				 PreparedStatement ps = connessione.prepareStatement(TAKE_ALL, Statement.RETURN_GENERATED_KEYS);
				 ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Prodotto p = new Prodotto();
					p.setId(rs.getInt("id_prodotto"));
					p.setId_categoria(rs.getInt("id_categoria"));
					p.setNome(rs.getString("nome"));
					p.setDescrizione(rs.getString("descrizione"));
					p.setPrezzo(rs.getDouble("prezzo"));
					p.setStock(rs.getInt("stock"));
					p.setUrl_img(rs.getString("url_immagine"));

					String categoria = rs.getString("nome_categoria");
					p.setCategoria(categoria);

					catalogo.computeIfAbsent(categoria, k -> new ArrayList<>()).add(p);
				}	

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return catalogo;
	}
}
