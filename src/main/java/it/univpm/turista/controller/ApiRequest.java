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

import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.turista.model.HistoricalResponse;
import it.univpm.turista.model.LiveResponse;

public class ApiRequest {
	
	private static final String http = "http://api.currencylayer.com/";
	private static final String access_Key = "?access_key=0fc8f664ff203059a234cd0cdcacf894";
	
	private static String currencies;
	private static String endpoint;
	private static String url;
	private static String data;
	
	
	// metodo per leggere un file e restituire una stringa
	
	private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
	}
	 
	// metodo per estrarre il json da l'url
	
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
	 
	 
	// metodo per estrarre dall'api i dati live
	
	public static void Liveparsing(String code) throws JSONException, IOException {
		
		currencies = "&currencies=" + code;
		endpoint = "live";
		url = http + endpoint + access_Key + currencies;
		
		ObjectMapper obj = new ObjectMapper();
		
		
			 	JSONObject json = readJsonFromUrl(url);
			 	
				LiveResponse live = obj.readValue(json.toString(), LiveResponse.class); 
			 		
	        }
	
	       // catch (JsonParseException e) {
	     //       e.printStackTrace();
	      //  }
	     //   catch (JsonMappingException e) {
	     //       e.printStackTrace();
	    //    }
	  //      catch (IOException e) {
	   //         e.printStackTrace();
	  //     }
		
		// System.out.print(live);
		// return live;}
		 	 	       
	

	
	
	
	
	// metodo per estrarre i dati storici dall'api
	public static LiveResponse  historicalparsing(String[] date, String code) {
		
			data = "&date=";
			currencies = "&currencies=" + code;
			endpoint = "historical";
			// url = http + endpoint + access_Key + currencies + data;
		
		HistoricalResponse historical = null;
		ObjectMapper obj = new ObjectMapper();
	       
		 try {
				 for(int i = 0; i<date.length; i++) {
				url= http + endpoint + access_Key + currencies + data + date[i]; 
			 
				 JSONObject json = readJsonFromUrl(url);
				 historical = obj.readValue(json.toString(), HistoricalResponse.class);
	            
			 } 
	        }
	        catch (JsonParseException e) {
	            e.printStackTrace();
	        }
	        catch (JsonMappingException e) {
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		return historical;	
	}
}