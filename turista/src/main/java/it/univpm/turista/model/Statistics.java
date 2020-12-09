package it.univpm.turista.model;

import it.univpm.turista.util.Utilities;

/**
 * Statitics è la classe che implementa i metodi per il calcolo delle
 * statistiche delle valute
 *
 */

public class Statistics {

	/**
	 * metodo che calcola quanto varia la valuta tra il valore massimo e il valore
	 * minimo
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return la variazione della valuta
	 */

	public static double variazione(double[] val) {
		return Utilities.max(val) - Utilities.min(val);

	}

	/**
	 * metodo che calcola quanto varia in percentuale la valuta tra il valore
	 * massimo e il valore minimo
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return la variazione percentuale della valuta
	 */

	public static String var_percentuale(double[] val) {
		int n = val.length - 1;
		double perc = val[0] / val[n];
		String risultato = "";
		if (perc < 1) {
			risultato = "-" + perc + "%";
		}
		if (perc > 1) {
			double ris = perc - 1;
			risultato = "+" + ris + "%";
		}
		return risultato;
	}

	/**
	 * metodo che calcola la media della valuta
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return media della valuta
	 */
	public static double avg(double[] val) {
		return Utilities.sum(val) / val.length;
	}

	/**
	 * metodo che calcola le perdite assolute di una valuta
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return perdite assolute
	 */
	public static double per_ass(double[] per) {
		return Utilities.sum(per);
	}

	/**
	 * metodo che calcola le perdite media di una valuta
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return perdite media
	 */
	public static double per_avg(double[] per) {
		return Utilities.sum(per) / per.length;
	}

	/**
	 * metodo che calcola varianza di una valuta
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return varianza
	 */
	public static double varianza(double[] val) {
		double var = 0;

		for (int i = 0; i < val.length; i++) {
			double tmp = (val[i] - avg(val)) * (val[i] - avg(val));
			var += tmp;
		}
		return var / avg(val);
	}

}
