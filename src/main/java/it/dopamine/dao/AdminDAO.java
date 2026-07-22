package it.dopamine.dao;

import java.sql.*;
import java.util.*;
import it.dopamine.util.Connector;

public class AdminDAO {
	
    public List<Map<String, Object>> getAllProdotti() {
        List<Map<String, Object>> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotti";

        try (Connection conn = Connector.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> p = new HashMap<>();
                p.put("id", rs.getInt("id_prodotto"));
                p.put("nome", rs.getString("nome"));
                p.put("descrizione", rs.getString("descrizione"));
                p.put("prezzo", rs.getDouble("prezzo"));
                p.put("stock", rs.getInt("stock"));
                p.put("url_immagine", rs.getString("url_immagine"));
                prodotti.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    public List<Map<String, Object>> getAllUtenti() {
        List<Map<String, Object>> utenti = new ArrayList<>();
        String sql = "SELECT * FROM utenti";

        try (Connection conn = Connector.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> u = new HashMap<>();
                u.put("id", rs.getInt("id_utente"));
                u.put("nome", rs.getString("nome"));
                u.put("cognome", rs.getString("cognome"));
                u.put("email", rs.getString("email"));
                u.put("admin", rs.getBoolean("admin"));
                utenti.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    public List<Map<String, Object>> getAllOrdini() {
        List<Map<String, Object>> ordini = new ArrayList<>();
        String sql = "SELECT o.*, u.email FROM ordini o LEFT JOIN utenti u ON o.id_utente = u.id_utente ORDER BY o.data_ordine DESC";

        try (Connection conn = Connector.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Map<String, Object> o = new HashMap<>();
                o.put("id", rs.getInt("id_ordine"));
                o.put("id_utente", rs.getInt("id_utente"));
                o.put("email_utente", rs.getString("email"));
                o.put("totale", rs.getDouble("totale"));
                o.put("stato", rs.getString("stato"));
                o.put("data_ordine", rs.getTimestamp("data_ordine"));
                ordini.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
    }

    public boolean salvaProdotto(String idStr, String nome, String desc, double prezzo, int stock, String urlImg) {
        if (idStr == null || idStr.trim().isEmpty()) {
            String sql = "INSERT INTO prodotti (nome, descrizione, prezzo, stock, url_immagine, id_categoria) VALUES (?, ?, ?, ?, ?, 1)";
            try (Connection conn = Connector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nome);
                ps.setString(2, desc);
                ps.setDouble(3, prezzo);
                ps.setInt(4, stock);
                ps.setString(5, urlImg);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "UPDATE prodotti SET nome=?, descrizione=?, prezzo=?, stock=?, url_immagine=? WHERE id_prodotto=?";
            try (Connection conn = Connector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nome);
                ps.setString(2, desc);
                ps.setDouble(3, prezzo);
                ps.setInt(4, stock);
                ps.setString(5, urlImg);
                ps.setInt(6, Integer.parseInt(idStr));
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean eliminaProdotto(int id) {
        String sql = "DELETE FROM prodotti WHERE id_prodotto=?";
        try (Connection conn = Connector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean toggleAdminUtente(int id, boolean currentAdmin) {
        String sql = "UPDATE utenti SET admin=? WHERE id_utente=?";
        try (Connection conn = Connector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, !currentAdmin);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminaUtente(int id) {
        String sql = "DELETE FROM utenti WHERE id_utente=?";
        try (Connection conn = Connector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean aggiornaStatoOrdine(int id, String nuovoStato) {
        String sql = "UPDATE ordini SET stato=? WHERE id_ordine=?";
        try (Connection conn = Connector.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuovoStato);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminaOrdine(int id) {
        try (Connection conn = Connector.getConnection()) {
            try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM ordini_items WHERE id_ordine=?")) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }
            try (PreparedStatement ps2 = conn.prepareStatement("DELETE FROM ordini WHERE id_ordine=?")) {
                ps2.setInt(1, id);
                return ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}