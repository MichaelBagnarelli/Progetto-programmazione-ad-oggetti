package it.univpm.turista.exception;

/**
 * codeError rappresenta un'eccezione personalizzata
 * Si verifica quando la il codice della valuta inserito non
 * è contenuto nel dataset
 *
 */

public class codeException extends Exception {
	
	public codeException(String messaggio) {
		super(messaggio);
	}
}
