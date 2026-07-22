package it.dopamine.model;

import java.sql.Date;

public class Ordine {
    private int id;
    private int id_utente;
    private String stato;
    private double prezzo_totale;
    private Date data_ordine;

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }
    
    
    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }
    public void setData_ordine(Date data_ordine) {
        this.data_ordine = data_ordine;
    }
    public void setStato(String stato) {
    	this.stato = stato;
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getId_utente() {
        return id_utente;
    }
    public String getStato() {
        return stato;
    }
    public double getPrezzo_totale() {
        return prezzo_totale;
    }
    public Date getData_ordine() {
        return data_ordine;
    }
}