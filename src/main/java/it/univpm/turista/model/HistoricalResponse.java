package it.univpm.turista.model;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalResponse extends LiveResponse {
	
	
	@JsonProperty("historical")
	private boolean historical;

	@JsonProperty("date")
	private Date date;

	@JsonCreator
	public HistoricalResponse(@JsonProperty("success") boolean success, @JsonProperty("terms") String terms,@JsonProperty("privacy") String privacy,
			@JsonProperty("historical") boolean historical, @JsonProperty("date") Date date, @JsonProperty("timestamp") Long timestamp,
			@JsonProperty("source") String source, @JsonProperty("quotes") Map<String, Double> quotes) {
		
		super(success, terms, privacy, timestamp, source, quotes);
		this.historical = historical;
		this.date = date;
	}

	public boolean isHistorical() {
		return historical;
	}

	public void setHistorical(boolean historical) {
		this.historical = historical;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Data: " + this.date + "\n" 
				+ "You currency: " + this.getSource() + "\n"
				+ "currency changed: " + this.getQuotes();
	}
}
