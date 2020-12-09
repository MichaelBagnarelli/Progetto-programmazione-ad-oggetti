package it.univpm.turista.model;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LiveResponse {
	
	@JsonProperty("success")
	private boolean success;
	
	@JsonProperty("terms")
	private String terms;
	
	@JsonProperty("privacy")
	private String privacy;
	
	@JsonProperty("timestamp")
	private Long timestamp;
	
	@JsonProperty("source")
	private String source;
	
	@JsonProperty("quotes")
	private Map<String, Double> quotes;

	

	@JsonCreator
	public LiveResponse(@JsonProperty("success") boolean success,@JsonProperty("terms") String terms,@JsonProperty("privacy") String privacy,
			@JsonProperty("timestamp") Long timestamp, @JsonProperty("source") String source, @JsonProperty("quotes") Map<String, Double> quotes) {
		this.success = success;
		this.terms = terms;
		this.privacy = privacy;
		this.timestamp = timestamp;
		this.source = source;
		this.quotes = quotes;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Map<String, Double> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<String, Double> quotes) {
		this.quotes = quotes;
	}
	

	@Override
	public String toString() {
		return "currency changed: " + quotes;
				
	}

	
}
