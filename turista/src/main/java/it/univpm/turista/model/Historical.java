package it.univpm.turista.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * HistoricalResponse è la classe sui cui vengono mappati i dati storici
 *
 */

public class Historical extends Live {
	
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
