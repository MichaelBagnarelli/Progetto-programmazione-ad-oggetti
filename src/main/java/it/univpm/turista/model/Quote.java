package it.univpm.turista.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {
	
	@JsonProperty("codice")
	private String codice;
	
	@JsonProperty("tassi")
	private double tassi;
	
	@JsonProperty("perdita")
	private double perdita;

	
	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public double getTassi() {
		return tassi;
	}

	public void setTassi(double tassi) {
		this.tassi = tassi;
	}

	public double getPerdita() {
		return perdita;
	}

	public void setPerdita(double perdita) {
		this.perdita = perdita;
	}

	@Override
	public String toString() {
		return codice + tassi;
	}
	
	
}
	
	
	
