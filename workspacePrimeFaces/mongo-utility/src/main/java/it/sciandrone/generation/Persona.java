package it.sciandrone.generation;

import java.util.List;

public class Persona {
	
	private String nome;
	private String cognome;
	private int eta;
	
	private List<Indirizzo> listaIndirizzi;

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

	public List<Indirizzo> getListaIndirizzi() {
		return listaIndirizzi;
	}

	public void setListaIndirizzi(List<Indirizzo> listaIndirizzi) {
		this.listaIndirizzi = listaIndirizzi;
	}
	
	

}
