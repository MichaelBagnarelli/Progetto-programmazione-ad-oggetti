package it.univpm.turista.model;

import it.univpm.turista.controller.ApiRequest;

public class Statistic {
	
	static double var;
	static String var_per;
	static double per_ass;
	static double avg;
	static double per_avg;
	static double varianza;
	
	
	public static void hisorical(String[] date, String code, String code2, double denaro) {
		
		double[] val = null;
		for(int i=0; i<date.length; i++) {
			LiveResponse his = ApiRequest.historicalparsing(date, code);
			val[i] = changeCurrency.currencyRate(his, code, code2).getTassi();	
		}
		
		double[] per = null;
		for(int i=0; i<date.length; i++) {
			LiveResponse his = ApiRequest.historicalparsing(date, code);
			per[i] = changeCurrency.perdita(his, code, code2, denaro)	;
		}
		
		
		var = variazione(val);
		var_per = var_percentuale(val);
		avg = avg(val);
		per_ass = per_ass(per);
		per_avg = per_avg(per);
		varianza = varianza(val);
}
	
	public static double max(double[] val) {
		double max = val[0];
		for(int i = 0; i<val.length; i++) {	
				if (val[i] > max) {
					max = val[i];  
			    }
		} return max;
	}
	
	public static double min(double[] val) {
		double min = val[0];
	for(int i = 0; i<val.length; i++) {	
			if (val[i] < min) {
			min = val[i];  
	    	}
		} return min;
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
	
	
	
	
	public static double variazione(double[] val) {
		 return max(val)-min(val);

}
	
	
	private static String var_percentuale(double[] val) {
		int n = val.length;
		double perc = val[0]/val[n]*100;
		String risultato  = "";
		if(perc<1) {
			risultato =  "-" + perc + "%";
		} if(perc>1) {
			double ris = perc-1;
			risultato = "+" + ris + "%";	
		} return risultato;
	}
	
	
	private static double avg(double[] val) {
		return sum_rate(val) / val.length;
	}
	
	
	private static double per_ass(double[] per) {
		return sum_perdita(per);
	}
	
	private static double per_avg(double[] per) {
		return sum_perdita(per) / per.length;
	}

	private static double varianza(double[] val) {
		double var = 0;
		
		for(int i = 0; i<val.length; i++) {
			double tmp = (val[i] - avg(val))*(val[i]-avg(val));
			var += tmp;	 
		}
		return var / avg(val);
	}
	
}
