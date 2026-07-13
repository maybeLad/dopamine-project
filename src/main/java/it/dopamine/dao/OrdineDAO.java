package it.dopamine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.dopamine.util.Connector;

public class OrdineDAO {
	public int getNumberOfOrders(int id_utente) {
		String COUNT_ORDERS = "SELECT COUNT(id_ordini) as OrdersUser FROM ordini WHERE id_utente = ?";
		int counter = 0;
		
		try(Connection connessione = Connector.getConnection();
				PreparedStatement ps = connessione.prepareStatement(COUNT_ORDERS);
				){
			
			ps.setInt(1, id_utente);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					counter = rs.getInt("OrdersUser");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return counter;
	}
}
