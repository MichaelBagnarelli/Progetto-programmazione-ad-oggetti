package it.univpm.turista.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.turista.model.HistoricalResponse;
import it.univpm.turista.model.LiveResponse;

/**
 * ApiRequest è la classe che genera la richiesta all'API currencylayer
 */

public class ApiRequest {

	private static final String http = "http://api.currencylayer.com/";
	private static final String access_Key = "?access_key=1b2b4690da1e104760f6e64731ea17a5";
	private static String endpoint;
	private static String url;
	private static String data;

	/**
	 * Metodo che estrae il json dall'url
	 *
	 * @param url contiene l'url da cui estrarre il json
	 * @throws JSONException
	 * @throws IOException
	 * @return Json contiene i dati ricevuti dall'API in formato Json
	 */

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	/**
	 * Metodo che permette di leggere una stringa del file Json
	 *
	 * @param rd stringa da leggere
	 * @throws IOException
	 * @return dati ricevuti dall'API in formato di stringa
	 */

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Metodo che restituisce i dati attuali
	 * 
	 * @throws JSONException
	 * @throws IOException
	 * @return live contiene i dati ricevuti dall'API
	 */

	public static LiveResponse RichiestaLive() throws JSONException, IOException {

		endpoint = "live";
		url = http + endpoint + access_Key;

		ObjectMapper obj = new ObjectMapper();
		JSONObject json = readJsonFromUrl(url);

		LiveResponse live = obj.readValue(json.toString(), LiveResponse.class);
		return live;
	}

	/**
	 * Metodo che restituisce i dati storici
	 * 
	 * @throws JSONException
	 * @throws IOException
	 * @return historical contiene i dati ricevuti dall'API
	 */

	public static HistoricalResponse RichiestaStorica(String date) throws JSONException, IOException {

		data = "&date=" + date;
		endpoint = "historical";
		url = http + endpoint + access_Key + data;

		ObjectMapper obj = new ObjectMapper();
		JSONObject json = readJsonFromUrl(url);
		HistoricalResponse historical = obj.readValue(json.toString(), HistoricalResponse.class);

		return historical;
	}

}