package it.univpm.turista.model;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Live è la classe sui cui vengono mappati i dati attuali(odierni)
 *
 */

public class Live {

	/**
	 * indica se la richiesta dei dati è andata a buon fine
	 */
	@JsonProperty("success")
	private boolean success;

	/**
	 * indica il link per termini e condizioni di currencylayer
	 */
	@JsonProperty("terms")
	private String terms;

	/**
	 * indica il link per la privacy di currencylayer
	 */
	@JsonProperty("privacy")
	private String privacy;

	/**
	 * indica l'esatta data e ora (UNIX) in cui è stata effetuata la richiesta
	 */
	@JsonProperty("timestamp")
	private Long timestamp;

	/**
	 * indica il codice della valuta a cui vengono riferiti i dati (default=USD)
	 */
	@JsonProperty("source")
	private String source;

	/**
	 * indica i dati ottenuti dall'API
	 */
	@JsonProperty("quotes")
	private Map<String, Double> quotes;

	
	
	/**
	 * metodo che resituisce il codice della valuta
	 * 
	 * @return il codice della valuta alla quale sono riferiti i dati
	 */
	public String getSource() {
		return source;
	}

	/**
	 * metodo che modifica il codice della valuta
	 * 
	 * @param il codice della valuta in base alla quale si vogliono calcolare i dati
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * metodo che resituisce le chiavi e i valori
	 * 
	 * @ i codici e i tassi del cambio valuta
	 */
	public Map<String, Double> getQuotes() {
		return quotes;
	}

	/**
	 * metodo che modifica il valore delle quote
	 * 
	 * @param i codici e i tassi del cambio valuta
	 */
	public void setQuotes(Map<String, Double> quotes) {
		this.quotes = quotes;
	}

}
