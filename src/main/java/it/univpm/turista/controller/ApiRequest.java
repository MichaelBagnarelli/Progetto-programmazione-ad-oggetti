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
	
	private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
	}
	 
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
	 
	 
	public static LiveResponse Liveparsing(String json) {
		 
		 LiveResponse live = null;
		 ObjectMapper obj = new ObjectMapper();
	       
		 try {
	            live = obj.readValue(json, LiveResponse.class);
	            System.out.println(live);
	            
	            System.out.println(live.getQuotes().get(live.getSource()+"EUR"));
	            
	        }
	        catch (JsonParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (JsonMappingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		return live;		 	       
	 }		
	
	public static LiveResponse historicalparsing(String json) {
		 
		 HistoricalResponse historical = null;
		 ObjectMapper obj = new ObjectMapper();
	       
		 try {
			 historical = obj.readValue(json, HistoricalResponse.class);
	            System.out.println(historical);
	            
	            System.out.println(historical.getQuotes().get(historical.getSource()+"EUR"));
	            
	        }
	        catch (JsonParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (JsonMappingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		return historical;	
	}
}
	
	
	
	


