package it.univpm.turista.controller;

import java.io.IOException;

import org.json.JSONException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.turista.model.HistoricalResponse;
import it.univpm.turista.model.LiveResponse;

import it.univpm.turista.model.Statistics;

import it.univpm.turista.model.changeCurrency;

/**
 * controller che gstisce le richieste
 */

@RestController
public class controller {

	/**
	 * Metodo che restuituisce i dati presenti nel json
	 * 
	 * @throws JSONException
	 * @throws IOException
	 * @return dati Json
	 */

	@RequestMapping(method = { RequestMethod.GET }, value = "/data", produces = "application/json")
	public String getvalue() throws JSONException, IOException {
		LiveResponse currency = ApiRequest.RichiestaLive();
		return currency.getQuotes().toString();

	}

	/**
	 * Metodo che gestisce la richiesta del tasso di cambio
	 *
	 * @param code  codice della propria valuta
	 * @param code2 codice della valutu su cui effetuare il cambio
	 * @throws JSONException
	 * @throws IOException
	 * @return stringa dove viene riportato il tasso di cambio
	 */

	@RequestMapping(method = { RequestMethod.GET }, value = "/tasso", produces = "aplication/json")
	public String getvaori(@RequestParam String code, String code2) throws JSONException, IOException {
		LiveResponse live = ApiRequest.RichiestaLive();
		double ris = changeCurrency.currencyRate(live, code, code2);
		return code + " to " + code2 + ": " + ris;
	}
	
	/**
	 * Metodo che gestisce la richiesta delle perdite dovute al cambio
	 *
	 * @param code   codice della propria valuta
	 * @param code2  codice della valuta su cui effetuare il calcolo della perdita
	 * @param denaro è il denaro che viene investito nel cambio di valuta
	 * @throws JSONException
	 * @throws IOException
	 * @return stringa dove viene riportato il valore della perdita
	 */

	@RequestMapping(method = { RequestMethod.GET }, value = "/perdita", produces = "aplication/json")
	public String getPerdita(@RequestParam String code, String code2, double denaro) throws JSONException, IOException {
		LiveResponse live = ApiRequest.RichiestaLive();
		double ris = changeCurrency.perdita(live, code, code2, denaro);
		return "perdità: " + ris;
	}

	/**
	 * Metodo che gestisce la richiesta delle statistiche sulla valuta
	 *
	 * @param code   codice della propria valuta
	 * @param code2  codice della valuta su cui effetuare le statistiche
	 * @param denaro è il denaro che viene investito nel cambio di valuta
	 * @param date   è array contente le date su cui effetuare le statistiche
	 * @throws JSONException
	 * @throws IOException
	 * @return stringa dove vengono riportate le statistiche
	 */

	@RequestMapping(method = { RequestMethod.GET }, value = "/stats", produces = "aplication/json")
	public String getStats(@RequestParam String code, String code2, double denaro, String[] date)
			throws JSONException, IOException {

		double[] ris = new double[date.length];
		double[] per = new double[date.length];

		// salvataggio dei valori in un array
		if (date != null) {
			for (int i = 0; i < date.length; i++) {
				HistoricalResponse his = ApiRequest.RichiestaStorica(date[i]);
				ris[i] = changeCurrency.currencyRate(his, code, code2);

			}
		}
		// calcolo statistiche
		double variazione = Statistics.variazione(ris);
		String var_percentuale = Statistics.var_percentuale(ris);
		double media = Statistics.avg(ris);
		double varianza = Statistics.varianza(ris);

		// salvataggio dei valori delle perdite
		if (date != null) {
			for (int i = 0; i < date.length; i++) {
				HistoricalResponse his = ApiRequest.RichiestaStorica(date[i]);
				double tmp = changeCurrency.perdita(his, code, code2, denaro);
				per[i] = tmp;
			}
		}

		// calcolo statistiche sulle perdite
		double perditaAssoluta = Statistics.per_ass(per);
		double perditaMedia = Statistics.per_avg(per);

		// creazione della stringa
		StringBuilder str = new StringBuilder();
		str.append("Statistiche sulla valuta: \n");
		str.append("variazione: " + variazione + "\n");
		str.append("variazione percentuale: " + var_percentuale + "\n");
		str.append("media: " + media + "\n");
		str.append("varianza: " + varianza + "\n");
		str.append("perdite assulute rispetto a " + code2 + ": " + perditaAssoluta + "\n");
		str.append("perdite media rispetto a " + code2 + ": " + perditaMedia + "\n");

		return str.toString();
	}

	/**
	 * Metodo che gestisce la richiesta della scelta dello sportello che ha minori
	 * perdite di denaro
	 *
	 * @param code  codice della propria valuta
	 * @param code2 è array contente i codici delle valute tra cui scegliere
	 * @throws JSONException
	 * @throws IOException
	 * @return stringa dove viene riportato lo sportello con meno perdite di denaro
	 */

	@RequestMapping(method = { RequestMethod.GET }, value = "/sportello", produces = "aplication/json")
	public String sceltaSportello(@RequestParam String code, String[] coding) throws IOException {
		LiveResponse live = ApiRequest.RichiestaLive();
		String str = changeCurrency.cambiaSportello(live, code, coding);
		return "Sportello con meno perdità: " + str;
	}
	
}
