package it.univpm.turista.model;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * HistoricalResponse è la classe sui cui vengono mappati i dati storici
 *
 */

public class HistoricalResponse extends LiveResponse {
	
	/**
	 * indica se i dati sono dati storici
	 */
	@JsonProperty("historical")
	private boolean historical;

	/**
	 * indica la specifica data alla quale fanno riferimento i dati
	 */
	@JsonProperty("date")
	private Date date;

	
	/**
	 * costruttore della classe
	 */
	@JsonCreator
	public HistoricalResponse(@JsonProperty("success") boolean success, @JsonProperty("terms") String terms,@JsonProperty("privacy") String privacy,
			@JsonProperty("historical") boolean historical, @JsonProperty("date") Date date, @JsonProperty("timestamp") Long timestamp,
			@JsonProperty("source") String source, @JsonProperty("quotes") Map<String, Double> quotes) {
		
		super(success, terms, privacy, timestamp, source, quotes);
		this.historical = historical;
		this.date = date;
	}

	

	/**
	 * metodo che restituisce la data
	 * 
	 * @return la data a cui di riferiscono i dati
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * metodo che modifica la data
	 * 
	 * @param date la data in cui ci interessano i dati
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
}
