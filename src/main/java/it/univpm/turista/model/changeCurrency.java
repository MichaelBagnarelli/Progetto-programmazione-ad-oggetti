package it.univpm.turista.model;

public class changeCurrency {
	
	private static String defaultcurrency = "USD";
	
	public static Quote currencyRate(LiveResponse live, String code, String code2) {
		Quote rate = new Quote();
		Double finalrate;
		
		double rateCode = live.getQuotes().get(defaultcurrency + code);	
		double rateCode2 = live.getQuotes().get(defaultcurrency + code2);
		finalrate = rateCode2/rateCode;
		rate.setTassi(finalrate);
		rate.setCodice(code+code2);
		return rate;
	}

	
	public static double perdita(LiveResponse live, String code, String code2, Double denaro) {
		
		double risultato = 0;
		
		Quote rate = currencyRate(live, code, code2);
		risultato = rate.getTassi()*denaro - denaro;
		rate.setPerdita(risultato);
		return risultato; 
	}
	
	
	public String sceltaSportello(LiveResponse live, String code, String[] other, double denaro) {
		Quote cambio = new Quote();
		String[] cod = null;
		double[] perdite = null;
		
		for(int i = 0; i<other.length; i++) {
			// cambio.setCodice(code + other[i]);
			cod[i] = code + other[i];
			perdite[i] = perdita(live, code, other[i], denaro);	
		}
		
		double minimo = perdite[0];
		for(int i = 0; i<cod.length & i<perdite.length; i++) {
			      if(perdite[i]<minimo) {
			        minimo=perdite[i];
			        cambio.setCodice(cod[i]);
			        cambio.setPerdita(minimo);
			      }   
			   }
		
		return cambio.getCodice();
	}
		
}
