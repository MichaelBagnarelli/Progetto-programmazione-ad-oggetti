package it.univpm.turista.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.JSONException;

public class changeCurrency {

	private static String defaultcurrency = "USD";
	static final double denaro = 1.0;

	public static double currencyRate(LiveResponse live, String code, String code2) throws NullPointerException {
		
		double finalrate;
		double rateCode;
		double rateCode2;

		rateCode = live.getQuotes().get(defaultcurrency + code);
		rateCode2 = live.getQuotes().get(defaultcurrency + code2);
		finalrate = rateCode2 / rateCode;
		
		return finalrate;
	}

	
	public static double perdita(LiveResponse live, String code, String code2, Double denaro) throws IOException {

		double risultato = 0;

		double rate = currencyRate(live, code, code2);
		risultato = rate * denaro - denaro;

		return risultato;
	}

	public static double currencyRate(HistoricalResponse his, String code, String code2)
			throws JSONException, IOException {

		double rateCode;
		double ratecode2;

		rateCode = his.getQuotes().get(defaultcurrency + code);
		ratecode2 = his.getQuotes().get(defaultcurrency + code2);
		double finalrate = ratecode2 / rateCode;
		return finalrate;
	}

	public static double perdita(HistoricalResponse his, String code, String code2, Double denaro) throws IOException {

		double risultato = 0;

		double rate = currencyRate(his, code, code2);
		risultato = rate * denaro - denaro;

		return risultato;
	}

	public static String cambiaSportello(LiveResponse live, String code, String[] coding) throws IOException {

		double[] finalrate = new double[coding.length];
		String[] finalcode = new String[coding.length];
		

		for (int i = 0; i < coding.length; i++) {
			finalcode[i] = code + coding[i];
			finalrate[i] = changeCurrency.perdita(live, code, coding[i], denaro);
		}

		Map<String, Double> map = new HashMap<String, Double>();
		for (int i = 0; i < coding.length; i++) {
			map.put(finalcode[i], finalrate[i]);
		}

		double massimo = finalrate[0];

		for (int i = 0; i < finalcode.length && i < finalrate.length; i++) {
			if (finalrate[i] > massimo) {
				massimo = finalrate[i];
			}
		}

		String ris = "";
		Set<Entry<String, Double>> mapping = map.entrySet();
		for (Entry<String, Double> e : mapping)
			if (massimo == e.getValue()) {
				ris = e.getKey();
			}

		return ris;
	}
}
