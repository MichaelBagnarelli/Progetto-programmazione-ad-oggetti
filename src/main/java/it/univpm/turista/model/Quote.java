package it.univpm.turista.model;


import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {
	
	@JsonProperty("quote")
	HashMap<String, Double> quote;
	// @JsonProperty("currencyCode")
	// private String currencyCode;
	
	//@JsonProperty("currencyCode")
	//private Double value;
	
	@JsonCreator
	public Quote() {
		
	}
	
	public Quote(HashMap<String, Double> quote) {
		super();
		this.quote = quote;
	}

	

	//public String getCurrencyCode() {
	//return this.currencyCode; }

	//public void setCurrencyCode(String code1, String code2) {
		//	this.currencyCode = code1+code2;}



	//public Double getValue() {
	//	return this.value;}



	//public void setValue(Double value) {
	//	this.value = value;}



	public HashMap<String, Double> getQuote() {
		return quote;
	}

	public void setQuote(HashMap<String, Double> quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "ciao";
	}

	
}
	
	
	
