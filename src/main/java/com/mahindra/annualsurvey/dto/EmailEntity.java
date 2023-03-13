package com.mahindra.annualsurvey.dto;


import com.microsoft.azure.storage.table.TableServiceEntity;

public class EmailEntity extends TableServiceEntity {

	public EmailEntity(String emailId,String time) {
		// TODO Auto-generated constructor stub
		this.partitionKey = emailId;
        this.rowKey = time;
	}
	
	String emailId;
	String time;
	String userId;
	String sectorId;

	
	
	
	public String getSectorId() {
		return sectorId;
	}
	public void setSectorId(String sectorId) {
		this.sectorId = sectorId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
