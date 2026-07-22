package it.dopamine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.dopamine.model.Ordine;
import it.dopamine.util.Connector;

public class OrdineDAO {

    public int getNumberOfOrders(int id_utente) {
        String COUNT_ORDERS = "SELECT COUNT(id_ordine) as OrdersUser FROM ordini WHERE id_utente = ?";
        int counter = 0;
        
        try (Connection connessione = Connector.getConnection();
             PreparedStatement ps = connessione.prepareStatement(COUNT_ORDERS)) {
            
            ps.setInt(1, id_utente);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    counter = rs.getInt("OrdersUser");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return counter;
    }
    
    public int creaOrdine(int idUtente, double totale, String indirizzo, String metodoPagamento) {
        String SQL = "INSERT INTO ordini (id_utente, totale, data_ordine, stato) VALUES (?, ?, CURRENT_TIMESTAMP, 'in_lavorazione')";
        int idOrdineGenerato = -1;

        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idUtente);
            ps.setDouble(2, totale);

            int righe = ps.executeUpdate();
            if (righe > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idOrdineGenerato = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idOrdineGenerato;
    }

    public boolean aggiungiDettaglioOrdine(int idOrdine, int idProdotto, int quantita, double prezzoUnitario) {
        String SQL = "INSERT INTO ordini_items (id_ordine, id_prodotto, quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, idOrdine);
            ps.setInt(2, idProdotto);
            ps.setInt(3, quantita);
            ps.setDouble(4, prezzoUnitario);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Ordine> getOrdiniByUtente(int idUtente) {
        String SQL = "SELECT id_ordine, id_utente, totale, data_ordine, stato "
                   + "FROM ordini WHERE id_utente = ? ORDER BY data_ordine DESC";
        List<Ordine> ordini = new ArrayList<>();

        try (Connection conn = Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL)) {

            ps.setInt(1, idUtente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ordine o = new Ordine();
                    o.setId(rs.getInt("id_ordine"));
                    o.setId_utente(rs.getInt("id_utente"));
                    o.setPrezzo_totale(rs.getDouble("totale"));
                    o.setData_ordine(new java.sql.Date(rs.getTimestamp("data_ordine").getTime()));
                    o.setStato(rs.getString("stato"));

                    ordini.add(o);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordini;
    }
}