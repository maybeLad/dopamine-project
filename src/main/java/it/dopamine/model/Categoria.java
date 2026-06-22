package it.dopamine.model;

public class Categoria {
    private int id;
    private String nome;
    private String descrizione;
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
}