package it.univpm.turista.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.JSONException;

/**
 * changeCurrency è la classe che implementa i metodi per l'analisi delle
 * richieste
 *
 */

public class changeCurrency {

	private static String defaultcurrency = "USD";
	static final double denaro = 1.0;

	/**
	 * Metodo che effettua il calcolo del tasso di cambio tra due valute (per dati
	 * attuali)
	 *
	 * @param live  contiene i dati su cui applicare il metodo
	 * @param code  codice della propria valuta
	 * @param code2 codice della valuta su cui effetuare il cambio
	 * @throws JSONException
	 * @throws IOException
	 * @return finalRate il valore del tasso
	 */

	public static double currencyRate(LiveResponse live, String code, String code2) throws NullPointerException {

		double finalrate;
		double rateCode;
		double rateCode2;

		rateCode = live.getQuotes().get(defaultcurrency + code);
		rateCode2 = live.getQuotes().get(defaultcurrency + code2);
		finalrate = rateCode2 / rateCode;

		return finalrate;
	}

	/**
	 * Metodo che effettua il calcolo del tasso della perdita durante il cambio tra
	 * due valute
	 *
	 * @param live  contiene i dati attuali su cui applicare il metodo
	 * @param code  codice della propria valuta
	 * @param code2 codice della valuta su cui effetuare il calcolo della perdita
	 * @throws JSONException
	 * @throws IOException
	 * @return risultato il valore della perdiata dovuta al cambio
	 */

	public static double perdita(LiveResponse live, String code, String code2, Double denaro) throws IOException {

		double risultato = 0;

		double rate = currencyRate(live, code, code2);
		risultato = rate * denaro - denaro;

		return risultato;
	}

	/**
	 * Metodo che effettua il calcolo del tasso di cambio tra due valute (per dati
	 * storici)
	 *
	 * @param his   contiene i dati storici su cui applicare il metodo
	 * @param code  codice della propria valuta
	 * @param code2 codice della valuta su cui effetuare il cambio
	 * @throws JSONException
	 * @throws IOException
	 * @return finalRate il valore del tasso
	 */

	public static double currencyRate(HistoricalResponse his, String code, String code2)
			throws JSONException, IOException {

		double rateCode;
		double ratecode2;

		rateCode = his.getQuotes().get(defaultcurrency + code);
		ratecode2 = his.getQuotes().get(defaultcurrency + code2);
		double finalrate = ratecode2 / rateCode;
		return finalrate;
	}

	/**
	 * Metodo che effettua il calcolo del tasso della perdita durante il cambio tra
	 * due valute (per dati storici)
	 *
	 * @param his   contiene i dati storici su cui applicare il metodo
	 * @param code  codice della propria valuta
	 * @param code2 codice della valuta su cui effetuare il calcolo della perdita
	 * @throws JSONException
	 * @throws IOException
	 * @return risultato il valore della perdiata dovuta al cambio
	 */

	public static double perdita(HistoricalResponse his, String code, String code2, Double denaro) throws IOException {

		double risultato = 0;

		double rate = currencyRate(his, code, code2);
		risultato = rate * denaro - denaro;

		return risultato;
	}

	/**
	 * Metodo che effettua la scelta dello sportello che farà perdere meno denaro
	 *
	 * @param live   contiene i dati attuali su cui applicare il metodo
	 * @param code   codice della propria valuta
	 * @param codici stringa di codici delle valute su cui applicare la scelta
	 * @throws JSONException
	 * @throws IOException
	 * @return ris codice dello sportello che farà perdere meno denaro
	 */

	public static String cambiaSportello(LiveResponse live, String code, String[] codici) throws IOException {

		double[] finalrate = new double[codici.length];
		String[] finalcode = new String[codici.length];

		// scrittura codici sportelli e calcolo delle perdite per ogni sportello
		for (int i = 0; i < codici.length; i++) {
			finalcode[i] = code + codici[i];
			finalrate[i] = changeCurrency.perdita(live, code, codici[i], denaro);
		}

		// inserimento dati in un map
		Map<String, Double> map = new HashMap<String, Double>();
		for (int i = 0; i < codici.length; i++) {
			map.put(finalcode[i], finalrate[i]);
		}

		// calcolo della perdita minore
		double minimo = finalrate[0];

		for (int i = 0; i < finalcode.length && i < finalrate.length; i++) {
			if (finalrate[i] > minimo) {
				minimo = finalrate[i];
			}
		}

		// ricerca dello sportello in base al valore minimo
		String ris = "";
		Set<Entry<String, Double>> mapping = map.entrySet();
		for (Entry<String, Double> e : mapping)
			if (minimo == e.getValue()) {
				ris = e.getKey();
			}

		return ris;
	}
}
