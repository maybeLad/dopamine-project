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
    private String metodo_pagamento;
    private String carta_ultime_4_cifre;
    private String scadenza_carta;

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
    
    public void setMetodo_pagamento(String metodo_pagamento) {
		this.metodo_pagamento = metodo_pagamento;
	}
    
	public void setCarta_ultime_4_cifre(String carta_ultime_4_cifre) {
		this.carta_ultime_4_cifre = carta_ultime_4_cifre;
	}

	public void setScadenza_carta(String scadenza_carta) {
		this.scadenza_carta = scadenza_carta;
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

	public String getMetodo_pagamento() {
		return metodo_pagamento;
	}
	
	public String getCarta_ultime_4_cifre() {
		return carta_ultime_4_cifre;
	}

	public String getScadenza_carta() {
		return scadenza_carta;
	}
	
}