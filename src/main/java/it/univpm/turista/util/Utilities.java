package it.univpm.turista.util;

public class Utilities {
	public static double max(double[] val) {
		double max = val[0];
		for (int i = 0; i < val.length; i++) {
			if (val[i] > max) {
				max = val[i];
			}
		}
		return max;
	}

	public static double min(double[] val) {
		double min = val[0];
		for (int i = 0; i < val.length; i++) {
			if (val[i] < min) {
				min = val[i];
			}
		}
		return min;
	}

	public static double sum_rate(double[] val) {
		float tot = 0;
		for (int i = 0; i < val.length; i++)
			tot += val[i];
		return tot;
	}

	public static double sum_perdita(double[] per) {
		float tot = 0;
		for (int i = 0; i < per.length; i++)
			tot += per[i];
		return tot;
	}

}
