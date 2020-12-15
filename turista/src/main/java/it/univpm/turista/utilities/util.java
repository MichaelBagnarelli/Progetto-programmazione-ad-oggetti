package it.univpm.turista.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



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

		case "terms":
			str = "string";

		case "privacy":
			str = "string";

		case "timestamp":
			str = "long";

		case "source":
			str = "string";

		case "quotes":
			str = "Map<String,Double>";
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
	 */

	public static String[] date(String start, String end) throws ParseException {

		// calcolo differenza giorni tra le date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dayfrom = dateFormat.parse(start);
		Date dayto = dateFormat.parse(end);
		long diff = dayto.getTime() - dayfrom.getTime(); 
		int giorni = (int) (diff / (24 * 60 * 60 * 1000));
		
		
		// creazione array di date
		String convertedDate = "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormat.parse(start));
		String[] date = new String[giorni];

		for (int i = 0; i < giorni; i++) {
			cal.add(Calendar.DATE, 1);
			convertedDate = dateFormat.format(cal.getTime());
			date[i] = convertedDate;
			cal.setTime(dateFormat.parse(convertedDate));

		}
		return date;
	}

}
