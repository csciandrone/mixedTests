package it.sciandrone.mapper;

public class Azienda {

	public Azienda() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Azienda(String denominazione, int anniAttivita) {
		super();
		this.denominazione = denominazione;
		this.anniAttivita = anniAttivita;
	}


	private String denominazione;
	private int anniAttivita;
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public int getAnniAttivita() {
		return anniAttivita;
	}
	public void setAnniAttivita(int anniAttivita) {
		this.anniAttivita = anniAttivita;
	}
	
	

}
