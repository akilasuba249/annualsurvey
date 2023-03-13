package com.mahindra.annualsurvey.dto;

import java.util.List;

public class EconnectDataDTO {
	
	private String start_date;
	private String end_date;
	private List<Object> tokenId;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public List<Object> getTokenId() {
		return tokenId;
	}
	public void setTokenId(List<Object> tokenId) {
		this.tokenId = tokenId;
	}
	
	
	

}
