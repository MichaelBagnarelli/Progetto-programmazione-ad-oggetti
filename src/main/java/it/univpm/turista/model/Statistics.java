package it.univpm.turista.model;

import it.univpm.turista.util.Utilities;

public class Statistics {

	

	public static double variazione(double[] val) {
		return Utilities.max(val) - Utilities.min(val);

	}

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

	public static double avg(double[] val) {
		return Utilities.sum_rate(val) / val.length;
	}

	public static double per_ass(double[] per) {
		return Utilities.sum_perdita(per);
	}

	public static double per_avg(double[] per) {
		return Utilities.sum_perdita(per) / per.length;
	}

	public static double varianza(double[] val) {
		double var = 0;

		for (int i = 0; i < val.length; i++) {
			double tmp = (val[i] - avg(val)) * (val[i] - avg(val));
			var += tmp;
		}
		return var / avg(val);
	}

}
