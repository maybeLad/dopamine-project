package it.dopamine.model;

public class CarrelloItem {
	private Prodotto prodotto;
	private int quantita;

	public CarrelloItem() {
	}

	public CarrelloItem(Prodotto prodotto, int quantita) {
		this.prodotto = prodotto;
		this.quantita = quantita;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getSubtotale() {
		return prodotto != null ? prodotto.getPrezzo() * quantita : 0;
	}
}