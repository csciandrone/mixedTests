package it.sciandrone.mapper;

public class Persona {
	
	private String nome;
	private String cognome;
	private int eta;
	
	private String professione;
	
	
    
	public Persona(String nome, String cognome, int eta, String professione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.professione = professione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getProfessione() {
		return professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public Persona() {
	}

}
