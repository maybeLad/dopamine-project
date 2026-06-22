package it.dopamine.model;

public class Prodotto {
    private int id;
    private int id_categoria;
    private String nome;
    private String categoria;
    private String descrizione;
    private double prezzo;
    private int stock;
    private String url_img;

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getId_categoria() {
        return id_categoria;
    }
    public String getNome() {
        return nome;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public double getPrezzo() {
        return prezzo;
    }
    public int getStock() {
        return stock;
    }
    public String getUrl_img() {
        return url_img;
    }
}