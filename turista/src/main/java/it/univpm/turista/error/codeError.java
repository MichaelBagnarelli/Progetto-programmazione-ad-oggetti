package it.univpm.turista.error;

/**
 * codeError rappresenta un'eccezione personalizzata
 * Si verifica quando la il codice della valuta inserito non
 * è contenuto nel dataset
 *
 */

public class codeError extends Exception {
	
	public codeError(String messaggio) {
		super(messaggio);
	}
}
