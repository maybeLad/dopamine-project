package it.dopamine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import it.dopamine.model.Prodotto;
import it.dopamine.util.Connector;

public class ProdottoDAO {

    public Prodotto getProdotto(String name) {
        final String TAKE_PRODUCT = "SELECT * FROM prodotti WHERE nome = ?";
        Prodotto product = null;
        
        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(TAKE_PRODUCT)) {
            
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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
        final String TAKE_PRODUCT = "SELECT * FROM prodotti WHERE id_prodotto = ?";
        Prodotto product = null;
        
        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(TAKE_PRODUCT)) {
            
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new Prodotto();
                    product.setNome(rs.getString("nome"));
                    product.setId(id);
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
    

    public List<Prodotto> cercaProdotti(String query) {
        List<Prodotto> risultati = new ArrayList<>();

        final String SEARCH = "SELECT p.id_prodotto, p.id_categoria, p.nome, p.descrizione, "
                + "p.prezzo, p.stock, p.url_immagine, c.nome AS nome_categoria "
                + "FROM prodotti p "
                + "JOIN categorie c ON p.id_categoria = c.id_categoria "
                + "WHERE p.nome LIKE ? OR p.descrizione LIKE ? OR c.nome LIKE ? "
                + "ORDER BY p.nome";

        String likeQuery = "%" + query + "%";

        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(SEARCH)) {

            ps.setString(1, likeQuery);
            ps.setString(2, likeQuery);
            ps.setString(3, likeQuery);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prodotto p = new Prodotto();
                    p.setId(rs.getInt("id_prodotto"));
                    p.setId_categoria(rs.getInt("id_categoria"));
                    p.setNome(rs.getString("nome"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                    p.setStock(rs.getInt("stock"));
                    p.setUrl_img(rs.getString("url_immagine"));
                    p.setCategoria(rs.getString("nome_categoria"));

                    risultati.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return risultati;
    }
    
    public Prodotto getProdottoById(int idProdotto) {
        final String SQL = "SELECT * FROM prodotti WHERE id_prodotto = ?";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            
            ps.setInt(1, idProdotto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prodotto p = new Prodotto();
                    p.setId(rs.getInt("id_prodotto"));
                    p.setId_categoria(rs.getInt("id_categoria"));
                    p.setNome(rs.getString("nome"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                    p.setStock(rs.getInt("stock"));
                    p.setUrl_img(rs.getString("url_immagine"));
                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean scaricaStock(int idProdotto, int quantitaAcquistata) {
        final String SQL = "UPDATE prodotti SET stock = stock - ? WHERE id_prodotto = ? AND stock >= ?";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {
            
            ps.setInt(1, quantitaAcquistata);
            ps.setInt(2, idProdotto);
            ps.setInt(3, quantitaAcquistata);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Prodotto> getAllProdotti() {
        List<Prodotto> lista = new ArrayList<>();

        final String TAKE_ALL = "SELECT id_prodotto, nome FROM prodotti ORDER BY nome";

        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(TAKE_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id_prodotto"));
                p.setNome(rs.getString("nome"));

                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Map<String, List<Prodotto>> getProdottiFiltrati(String categoria, Double prezzoMin, Double prezzoMax) {
        Map<String, List<Prodotto>> catalogue = new LinkedHashMap<>();

        StringBuilder SELECT_PRODUCT_FILTRED = new StringBuilder(
            "SELECT p.id_prodotto, p.id_categoria, p.nome, p.descrizione, "
            + "p.prezzo, p.stock, p.url_immagine, c.nome AS nome_categoria "
            + "FROM prodotti p "
            + "JOIN categorie c ON p.id_categoria = c.id_categoria"
        );

        List<String> condizioni = new ArrayList<>();

        if (categoria != null && !categoria.trim().isEmpty() && !"tutte".equalsIgnoreCase(categoria)) 
            condizioni.add("c.nome = ?");
        if (prezzoMin != null) 
            condizioni.add("p.prezzo >= ?");
        if (prezzoMax != null) 
            condizioni.add("p.prezzo <= ?");
        if (!condizioni.isEmpty()) 
        	SELECT_PRODUCT_FILTRED.append(" WHERE ").append(String.join(" AND ", condizioni));
        
        SELECT_PRODUCT_FILTRED.append(" ORDER BY c.nome, p.nome");

        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(SELECT_PRODUCT_FILTRED.toString())) {

            int paramIndex = 1;

            if (categoria != null && !categoria.trim().isEmpty() && !"tutte".equalsIgnoreCase(categoria)) {
                ps.setString(paramIndex++, categoria);
            }
            if (prezzoMin != null) {
                ps.setDouble(paramIndex++, prezzoMin);
            }
            if (prezzoMax != null) {
                ps.setDouble(paramIndex++, prezzoMax);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prodotto p = new Prodotto();
                    p.setId(rs.getInt("id_prodotto"));
                    p.setId_categoria(rs.getInt("id_categoria"));
                    p.setNome(rs.getString("nome"));
                    p.setDescrizione(rs.getString("descrizione"));
                    p.setPrezzo(rs.getDouble("prezzo"));
                    p.setStock(rs.getInt("stock"));
                    p.setUrl_img(rs.getString("url_immagine"));

                    String cat = rs.getString("nome_categoria");
                    p.setCategoria(cat);

                    catalogue.computeIfAbsent(cat, k -> new ArrayList<>()).add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catalogue;
    }
}