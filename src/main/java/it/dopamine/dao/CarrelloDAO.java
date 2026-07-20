package it.dopamine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.dopamine.model.CarrelloItem;
import it.dopamine.model.Prodotto;
import it.dopamine.util.Connector;

public class CarrelloDAO {

	public boolean aggiungiProdotto(int idUtente, int idProdotto, int quantita) {
		final String CHECK = "SELECT quantita FROM carrello WHERE id_utente = ? AND id_prodotto = ?";
		boolean esiste = false;

		try (Connection conn = Connector.getConnection();
				PreparedStatement psCheck = conn.prepareStatement(CHECK)) {

			psCheck.setInt(1, idUtente);
			psCheck.setInt(2, idProdotto);

			try (ResultSet rs = psCheck.executeQuery()) {
				esiste = rs.next();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		final String SQL = esiste
				? "UPDATE carrello SET quantita = quantita + ? WHERE id_utente = ? AND id_prodotto = ?"
				: "INSERT INTO carrello (id_utente, id_prodotto, quantita) VALUES (?, ?, ?)";

		try (Connection conn = Connector.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL)) {

			if (esiste) {
				ps.setInt(1, quantita);
				ps.setInt(2, idUtente);
				ps.setInt(3, idProdotto);
			} else {
				ps.setInt(1, idUtente);
				ps.setInt(2, idProdotto);
				ps.setInt(3, quantita);
			}

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean rimuoviProdotto(int idUtente, int idProdotto) {
		final String DELETE = "DELETE FROM carrello WHERE id_utente = ? AND id_prodotto = ?";

		try (Connection conn = Connector.getConnection();
				PreparedStatement ps = conn.prepareStatement(DELETE)) {

			ps.setInt(1, idUtente);
			ps.setInt(2, idProdotto);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CarrelloItem> getCarrello(int idUtente) {
		List<CarrelloItem> items = new ArrayList<>();

		final String SELECT = "SELECT c.quantita, p.id_prodotto, p.id_categoria, p.nome, p.descrizione, "
				+ "p.prezzo, p.stock, p.url_immagine "
				+ "FROM carrello c "
				+ "JOIN prodotti p ON c.id_prodotto = p.id_prodotto "
				+ "WHERE c.id_utente = ?";

		try (Connection conn = Connector.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT)) {

			ps.setInt(1, idUtente);

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

					items.add(new CarrelloItem(p, rs.getInt("quantita")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return items;
	}

	public boolean svuotaCarrello(int idUtente) {
		final String DELETE = "DELETE FROM carrello WHERE id_utente = ?";

		try (Connection conn = Connector.getConnection();
				PreparedStatement ps = conn.prepareStatement(DELETE)) {

			ps.setInt(1, idUtente);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void unisciCarrelloOspite(int idUtente, Map<Integer, Integer> carrelloOspite) {
		if (carrelloOspite == null) {
			return;
		}

		for (Map.Entry<Integer, Integer> entry : carrelloOspite.entrySet()) {
			int idProdotto = entry.getKey();
			int quantita = entry.getValue();
			aggiungiProdotto(idUtente, idProdotto, quantita);
		}
	}
}