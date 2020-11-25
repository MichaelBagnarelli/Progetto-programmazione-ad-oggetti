package it.univpm.turista.controller;

import it.univpm.turista.model.LiveResponse;
import it.univpm.turista.model.HistoricalResponse;

import java.util.ArrayList;
import org.springframework.web.client.RestTemplate;



public class Request {
	
	private static final String ACCESS_CODE= "2dae350ac11c34e6e08b9b92cc5eb409";
	private static final String BASE_URL = "http://api.currencylayer.com/";
	
	public static ArrayList<LiveResponse> LiveRequest(String code)  {
		
		String ENDPOINT= "live";
		String url;
		
		url= BASE_URL + ENDPOINT +"?access_key=" + ACCESS_CODE + "&source=" + code + "&format=1";
		
		RestTemplate restTemplate = new RestTemplate();
	    ArrayList<LiveResponse> objects = new ArrayList<LiveResponse>();
	
	    for(LiveResponse live: restTemplate.getForObject(url, LiveResponse[].class)) {
	    	objects.add(live);
	    }
	    	return objects;
		}

	
	public static ArrayList<HistoricalResponse> HistoricalRequest() {
		
		String ENDPOINT= "historical";
		String url;
		
		url= BASE_URL + ENDPOINT +"?access_key=" + ACCESS_CODE + "&source=" + code + "&format=1";
		
		RestTemplate restTemplate = new RestTemplate();
	    ArrayList<HistoricalResponse> objects = new ArrayList<HistoricalResponse>();
	
	    for(HistoricalResponse b: restTemplate.getForObject(url, HistoricalResponse[].class)) {
	    	objects.add(b);
	    }
	    	return objects;
		}

}	


