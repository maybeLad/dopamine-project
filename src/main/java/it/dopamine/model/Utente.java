package it.dopamine.model;

public class Utente {
    private int id;
    private boolean admin;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String email;
    private String password;

    //Costruttore
    public Utente() {
        admin = false;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setAdmin() {
        admin = true;
    }
    public void removeAdmin() {
        admin = false;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Getters
    public int getId() {
        return id;
    }
    public boolean isAdmin() {
        return admin;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}