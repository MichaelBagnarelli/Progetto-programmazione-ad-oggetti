package it.univpm.turista.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.univpm.turista.error.dataError;



/**
 * util è la classe che implementa metodi utili per il progetto
 *
 */

public class util {

	/**
	 * metodo restituisce il tipo dei metadati
	 *
	 * @param s stringa di cui restituire il tipo
	 * @return tipo della stringa
	 */

	public static String tipo(String s) {
		String str = "";
		switch (s) {
		case "success": 
			str = "boolean";
			break;
		
		case "terms":
			str = "string";
			break;
		
		case "privacy": 
			str = "string";
			break;
		
		case "timestamp": 
			str = "long";
			break;
		case "source":
			str = "string";
			break;
		case "quotes":
			str = "Map<String,Double>";
			break;
		}
		return str;
	}

	/**
	 * metodo che resituisce un intervallo di date
	 *
	 * @param start la data di inizio
	 * @param end   la data finale
	 * @throws ParseException
	 * @return array contenente l'intevallo di date
	 * @throws dataError 
	 */

	public static String[] date(String start, String end) throws ParseException, dataError {
		String[] date = null;
		
		
		// calcolo differenza giorni tra le date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dayfrom = dateFormat.parse(start);
		Date dayto = dateFormat.parse(end);
		
		if(dayfrom.after(dayto)) {
			throw (new dataError("La data di fine analisi non è corretta"));
		}
		
		long diff = dayto.getTime() - dayfrom.getTime(); 
		int giorni = (int) (diff / (24 * 60 * 60 * 1000));
		
		// creazione array di date
		String convertedDate = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(start));
		date = new String[giorni];

		for (int i = 0; i < giorni; i++) {
			cal.add(Calendar.DATE, 1);
			convertedDate = dateFormat.format(cal.getTime());
			date[i] = convertedDate;
			cal.setTime(dateFormat.parse(convertedDate));

		}
		
		return date;
	}
}

