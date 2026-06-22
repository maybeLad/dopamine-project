package it.dopamine.model;

public class Recensione {
    private int id;
    private int id_utente;
    private int id_prodotto;
    private int voto;
    private String descrizione;
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }
    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }
    public void setVoto(int voto) {
        this.voto = voto;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getId_utente() {
        return id_utente;
    }
    public int getId_prodotto() {
        return id_prodotto;
    }
    public int getVoto() {
        return voto;
    }
    public String getDescrizione() {
        return descrizione;
    }
}