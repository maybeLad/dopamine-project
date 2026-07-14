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
import it.dopamine.util.Connector;

public class ProdottoDAO {
	public Prodotto getProdotto(String name) {
		final String TAKE_PRODUCT = "SELECT * FROM prodotti WHERE nome = ?" ;
		Prodotto product = null;
		
		try (Connection connessione = Connector.getConnection();
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
            		product.setStock(rs.getInt("stock"));
            		
            	}
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
		return product;
	}
	
	public Prodotto getProdotto(int id) {
		final String TAKE_PRODUCT = "SELECT * FROM prodotti WHERE id_prodotto = ?" ;
		Prodotto product = null;
		
		try (Connection connessione = Connector.getConnection();
			PreparedStatement ps = connessione.prepareStatement(TAKE_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
			
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()){
            	if(rs.next()) {
            		product = new Prodotto();
            		product.setNome(rs.getString("nome"));
            		product.setId(id);
            		product.setDescrizione(rs.getString("descrizione"));
            		product.setPrezzo(rs.getDouble("prezzo"));
            		product.setUrl_img(rs.getString("url_immagine"));
            		
            	}
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        
		return product;
	}
	
	public Map<String, List<Prodotto>> getProdottiRaggruppati() {
		Map<String, List<Prodotto>> catalogo = new LinkedHashMap<>();

		final String TAKE_ALL = "SELECT p.id_prodotto, p.id_categoria, p.nome, p.descrizione, "
				+ "p.prezzo, p.stock, p.url_immagine, c.nome AS nome_categoria "
				+ "FROM prodotti p "
				+ "JOIN categorie c ON p.id_categoria = c.id_categoria "
				+ "ORDER BY c.nome, p.id_prodotto";

		try (Connection connessione = Connector.getConnection(); 
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
				e.getMessage();
			}

			
		
		return catalogo;
	}



}

