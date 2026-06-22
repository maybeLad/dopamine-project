package it.dopamine.model;

public class Ordine_item {
    private int id;
    private int id_ordine;
    private int id_prodotto;
    private int quantita;
    private double prezzo_unitario;

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }
    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    public void setPrezzo_unitario(double prezzo_unitario) {
        this.prezzo_unitario = prezzo_unitario;
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getId_ordine() {
        return id_ordine;
    }
    public int getId_prodotto() {
        return id_prodotto;
    }
    public int getQuantita() {
        return quantita;
    }
    public double getPrezzo_unitario() {
        return prezzo_unitario;
    }
}