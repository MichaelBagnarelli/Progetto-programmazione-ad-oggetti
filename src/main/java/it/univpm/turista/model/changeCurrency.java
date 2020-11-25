package it.univpm.turista.model;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
// import java.util.Collection;
import java.util.Iterator;

import java.util.Map;

import it.univpm.turista.util.convertMaptoString;




public class changeCurrency {
	
	void change() {
		
		
	}
	
	void currencyRate(LiveResponse live,String code) {
				
		live.getQuotes().get(live.getSource()+ code);
	
	}
	
	
	void perdita (LiveResponse live, double denaro) {
		boolean saldoPositivo = true;
		double perdita;
		
		
		Iterator<Double> collection = live.getQuotes().values().iterator();
		
			}
		
		
		
	
	
	
	public static String changeSource(LiveResponse live) {
		
			String defaultcode = "USD";
			String youCode= "EUR";
			StringBuilder builder = new StringBuilder();
			Map<String, Double> map = live.getQuotes();
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
			 //   return builder.toString();
			   
			  String s= builder.toString();
			  s.replace(defaultcode, youCode);
			  System.out.println(s);
			  return s;
			  }
	
	
	public static void altro(LiveResponse live, String code) {
		String s = changeSource(live);
		Map<String, String> z = convertMaptoString.stringToMap(s);
		System.out.println(z);
		
	}
	}
		
		
		
		// String s;
		
		// if(code != live.getSource()) {
			// live.setSource(code);
		 // Map<String, Double> map = new HashMap<String, Double>();
		// map = live.getQuotes();
		 
		//Collection<String> key= map.keySet();
		//System.out.println(key);
		 //Iterator<String> keys =  map.keySet().iterator();
		// while(keys.hasNext()) {
		//	s = keys.next();
		//	s.replace(live.getSource(), code);	 
		//	System.out.println(s);
			// return s; } 
			
		 //else 
		 //return live.getSource();



