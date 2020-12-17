package it.univpm.turista.controller;

import java.io.IOException;

import java.text.ParseException;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import it.univpm.turista.model.Request;
import it.univpm.turista.exception.codeException;
import it.univpm.turista.exception.dataException;
import it.univpm.turista.model.Historical;
import it.univpm.turista.model.Live;
import it.univpm.turista.utilities.*;

/**
 * controller che gstisce le richieste
 */

@RestController
public class controller {

	final String http = "http://api.currencylayer.com/";
	final String access_Key = "?access_key=be1e0f992914201a08b44bc0077367c5";
	final String url = http + "live" + access_Key;
	
	/**
	 * Metodo che restuituisce i dati presenti nel json
	 * 
	 * @throws JSONException
	 * @throws IOException
	 * @return dati Json
	 */
	@RequestMapping(method = { RequestMethod.GET }, value = "/data", produces = "application/json")
	public Live getData() throws JSONException, IOException {
		JSONObject json = ApiRequest.readJsonFromUrl(url);
		JsonMapper mapper = new JsonMapper();
		return mapper.readValue(json.toString(), Live.class);
	}

	/**
	 * Metodo che restituisce i metadati e relativo tipo in formato json
	 *
	 * @throws JSONException
	 * @throws IOException
	 * @return metadati in formato json
	 */
	@RequestMapping(value = "/metadata", method = { RequestMethod.GET }, produces = "application/json")
	public String GetMeta() throws JSONException, IOException {

		JSONObject json = ApiRequest.readJsonFromUrl(url);
		Set<String> str = json.keySet();
		String ss = "[ ";
		for (String s : str) {
			ss += "{ " + " \"alias\" : " + s + "\n" + "		\"tipo\" : " + util.tipo(s) + "} " + "\n";
		}
		ss += "]";
		return ss;
	}

	/**
	 * Metodo che gestisce la richiesta del tasso di cambio
	 *
	 * @param body  contenente i codici delle valute 
	 * 		  per le quali è richiesto il tasso
	 * @throws JSONException
	 * @throws IOException
	 * @throws codeException 
	 * @throws NullPointerException 
	 * @return stringa dove viene riportato il tasso di cambio
	 */

	@RequestMapping(method = { RequestMethod.POST }, value = "/tasso", produces = "aplication/json")
	public String getTasso(@RequestBody String body) throws JSONException, IOException, NullPointerException, codeException {
		Live live = ApiRequest.RichiestaLive();
		ObjectMapper obj = new ObjectMapper();
		Request request = obj.readValue(body, Request.class);
		double tasso = currencyOperations.currencyRate(live, request.getCode(), request.getCode2());
		return request.getCode() + " to " + request.getCode2() + ": " + tasso;
	}

	/**
	 * Metodo che gestisce la richiesta delle perdite dovute al cambio
	 *
	 * @param body contenente i codici delle valute 
	 * e il denaro da cambiare
	 * @throws JSONException
	 * @throws IOException
	 * @throws codeException 
	 * @throws NullPointerException 
	 * @return stringa dove viene riportato il valore della perdita
	 */

	@RequestMapping(method = { RequestMethod.POST }, value = "/perdita", produces = "aplication/json")
	public String getPerdita(@RequestBody String body) throws JSONException, IOException, NullPointerException, codeException {
		Live live = ApiRequest.RichiestaLive();
		ObjectMapper obj = new ObjectMapper();
		Request request = obj.readValue(body, Request.class);
		double perdita = currencyOperations.perdita(live, request.getCode(), request.getCode2(), request.getDenaro());
		return "perdità: " + perdita;
	}

	/**
	 * Metodo che gestisce la richiesta delle statistiche sulla valuta
	 *
	 * @param body contenente i codici delle valute 
	 * e il denaro da cambiare
	 * @param start la data di inizio per l'analisi delle statistiche
	 * @param end la data di fine per l'analisi delle statistiche
	 * @throws JSONException errore sul file json
	 * @throws IOException
	 * @throws ParseException
	 * @throws dataException 
	 * @throws codeException 
	 * @throws NullPointerException 
	 * @return stringa dove vengono riportate le statistiche
	 */

	@RequestMapping(method = { RequestMethod.POST }, value = "/stats", produces = "aplication/json")
	public String getStats(@RequestParam String start, String end, @RequestBody String body) throws JSONException, IOException, ParseException, dataException, NullPointerException, codeException {
		ObjectMapper obj = new ObjectMapper();
		Request request = obj.readValue(body, Request.class);
		StringBuilder stats = new StringBuilder();
		
		try {
		String[] date = util.date(start, end);
		double[] val = new double[date.length];
		double[] per = new double[date.length];

		// salvataggio dei valori e delle perdite in un array e
		if (date != null) {
			for (int i = 0; i < date.length; i++) {
				Historical his = ApiRequest.RichiestaStorica(date[i]);

				val[i] = currencyOperations.currencyRate(his, request.getCode(), request.getCode2());
				per[i] = currencyOperations.perdita(his, request.getCode(), request.getCode2(), request.getDenaro());

			}
		}
	

		// calcolo statistiche
		double variazione = Statistics.variazione(val);
		String var_percentuale = Statistics.var_percentuale(val);
		double media = Statistics.avg(val);
		double varianza = Statistics.varianza(val);

		// calcolo statistiche sulle perdite
		double perditaAssoluta = Statistics.per_ass(per);
		double perditaMedia = Statistics.per_avg(per);

		// creazione della stringa
		
		stats.append("Statistiche sulla valuta: \n");
		stats.append("variazione: " + variazione + "\n");
		stats.append("variazione percentuale: " + var_percentuale + "\n");
		stats.append("media: " + media + "\n");
		stats.append("varianza: " + varianza + "\n");
		stats.append("perdite assulute rispetto a " + request.getCode2() + ": " + perditaAssoluta + "\n");
		stats.append("perdite media rispetto a " + request.getCode2() + ": " + perditaMedia + "\n");

		} catch (dataException err) {
			err.printStackTrace();
		} return stats.toString();
}	

	/**
	 * Metodo che gestisce la richiesta della scelta dello sportello che ha minori
	 * perdite di denaro
	 *
	 * @param body contenente il codice cella prorpia valuta
	 * @param codici codici degli sportelli di interesse
	 * @throws JSONException
	 * @throws IOException
	 * @throws codeException 
	 * @throws NullPointerException 
	 * @return stringa dove viene riportato lo sportello con meno perdite di denaro
	 */

	@RequestMapping(method = { RequestMethod.POST }, value = "/sportello", produces = "aplication/json")
	public String sceltaSportello(@RequestParam String[] codici, @RequestBody String body)
			throws JSONException, IOException, NullPointerException, codeException {

		Live live = ApiRequest.RichiestaLive();
		ObjectMapper obj = new ObjectMapper();
		Request request = obj.readValue(body, Request.class);

		String sportelloCode = currencyOperations.sceltaSportello(live, request.getCode(), codici);
		return "Sportello con meno perdità: " + sportelloCode;
	}

}
