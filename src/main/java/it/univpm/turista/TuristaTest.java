package it.univpm.turista;

import java.io.IOException;


import org.json.JSONException;
import org.json.JSONObject;
import it.univpm.turista.controller.ApiRequest;
import it.univpm.turista.model.LiveResponse;
import it.univpm.turista.model.changeCurrency;


public class TuristaTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JSONObject json;
		JSONObject json2;
		JSONObject json3;
		try {
			json = ApiRequest.readJsonFromUrl("http://api.currencylayer.com/live?access_key=7675e4521f1a2bb713b0638fa9fa36bd&source");
			System.out.println(json.toString());
			LiveResponse live = ApiRequest.Liveparsing(json.toString());
			
			json2 = ApiRequest.readJsonFromUrl("http://api.currencylayer.com/historical?access_key=7675e4521f1a2bb713b0638fa9fa36bd&source&date=2020-11-02");
			json3 =	ApiRequest.readJsonFromUrl("http://api.currencylayer.com/historical?access_key=7675e4521f1a2bb713b0638fa9fa36bd&source&date=2010-01-24");
			System.out.println(json.toString());
			LiveResponse his2 = ApiRequest.historicalparsing(json2.toString());
			LiveResponse his3 = ApiRequest.historicalparsing(json3.toString());
			
			
		
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}















}
