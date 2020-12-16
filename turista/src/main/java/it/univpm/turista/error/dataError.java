package it.univpm.turista.error;

/**
 * dataError rappresenta un'eccezione personalizzata
 * Si verifica quando la data finale è più vecchia 
 * rispetto a quella iniziale
 *
 */

public class dataError extends Exception {

	public dataError(String messaggio) {
		super(messaggio);
	}
}
