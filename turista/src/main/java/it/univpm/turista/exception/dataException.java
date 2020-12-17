package it.univpm.turista.exception;

/**
 * dataError rappresenta un'eccezione personalizzata
 * Si verifica quando la data finale è più vecchia 
 * rispetto a quella iniziale
 *
 */

public class dataException extends Exception {

	public dataException(String messaggio) {
		super(messaggio);
	}
}
