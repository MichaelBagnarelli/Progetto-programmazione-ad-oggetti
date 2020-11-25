package it.univpm.turista.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import java.util.HashMap;
import java.util.Map;

import it.univpm.turista.model.LiveResponse;

public class convertMaptoString {
	
	public static String mapToString(LiveResponse live) {  
		
		StringBuilder builder = new StringBuilder();
		Map<String, Double> map = live.getQuotes();;
		for (String key : map.keySet()) {  
		    if (builder.length() > 0) {  
		    	builder.append(", ");  
		    }  
		    double value = map.get(key);  
		    
		   
		    builder.append(key);  
		    builder.append("=");  
		    builder.append(value);  
		     
		   }  
		 	// System.out.println(builder.toString());
		   return builder.toString();
		  
	}



		  
	public static Map<String, String> stringToMap(String input) {  
		   Map<String, String> map = new HashMap<String, String>();  
		   
		   String[] nameValuePairs = input.split("&");  
		   for (String nameValuePair : nameValuePairs) {  
		    String[] nameValue = nameValuePair.split("=");  
		    try {  
		     map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1 ? URLDecoder.decode(  
		     nameValue[1], "UTF-8") : "");  
		    } catch (UnsupportedEncodingException e) {  
		     throw new RuntimeException("This method requires UTF-8 encoding support", e);  
		    }  
		   }  
		  
		   return map;  
		  }
}


