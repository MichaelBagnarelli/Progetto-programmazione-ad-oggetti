package it.univpm.turista.util;

/**
 * Utilities è la classe che implementa metodi utili per il progetto
 *
 */

public class Utilities {

	/**
	 * metodo che calcola il massimo dei valori
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return massimo
	 */
	public static double max(double[] val) {
		double max = val[0];
		for (int i = 0; i < val.length; i++) {
			if (val[i] > max) {
				max = val[i];
			}
		}
		return max;
	}

	/**
	 * metodo che calcola il minimo dei valori
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return minimo
	 */
	public static double min(double[] val) {
		double min = val[0];
		for (int i = 0; i < val.length; i++) {
			if (val[i] < min) {
				min = val[i];
			}
		}
		return min;
	}

	/**
	 * metodo che calcola la somma dei valori
	 *
	 * @param val stringa di valori su cui fare il calcolo
	 * @return somma
	 */

	public static double sum(double[] val) {
		float tot = 0;
		for (int i = 0; i < val.length; i++)
			tot += val[i];
		return tot;
	}

}
