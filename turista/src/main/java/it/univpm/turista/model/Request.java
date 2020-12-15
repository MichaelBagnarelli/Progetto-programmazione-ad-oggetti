package it.univpm.turista.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request  è la classe sulla quale viene mappata la richiesta
 *
 */

public class Request {
	
	/**
	 * indice il codice della propria valuta
	 */
	@JsonProperty("code")
	private String code;
	
	/**
	 * indica il codice della valuta per la quale si vuole effetuare il cambio
	 */
	@JsonProperty("code2")
	private String code2;
	
	/**
	 *  indica quanto denaro si desidera cambiare
	 */
	@JsonProperty("denaro")
	private double denaro;
	

	/**
	 * metodo che resituisce il codice della propria valuta
	 * 
	 * @return il codice della propria valuta
	 */
	public String getCode() {
		return code;
	}

	/**
	 * metodo che modifica il codice della valuta
	 * 
	 * @param il codice della propria valuta
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * metodo che resituisce il codice della valuta
	 * per la quale si vuole effetuare il cambio
	 * 
	 * @return il codice della valuta per la quale si vuole effetuare il cambio
	 */
	public String getCode2() {
		return code2;
	}

	/**
	 * metodo che modifica il codice della valuta 
	 * per la quale si vuole effetuare il cambio
	 * 
	 * @param il codice della valuta per la quale si vuole effetuare il cambio
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}

	
	/**
	 * metodo che resituisce il denaro 
	 * 
	 * @return il denaro che si desidera cambiare
	 */
	public double getDenaro() {
		return denaro;
	}

	/**
	 * metodo che modifica il denaro
	 * 
	 * @param denaro che si desidera cambaire
	 */
	public void setDenaro(double denaro) {
		this.denaro = denaro;
	}
	
}
