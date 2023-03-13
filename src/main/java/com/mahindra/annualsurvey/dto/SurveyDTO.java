/**
 * 
 */
package com.mahindra.annualsurvey.dto;

import java.util.Date;

/**
 * @author Yash Mahale
 *
 */
public class SurveyDTO {
	
	
	private int surveyId;
	
	private java.sql.Date startDate;
	
	private java.sql.Date endDate;
	
	private Date lastDate;
	
	private java.sql.Date nextDate;
	
	private int frequency;
	
	private Date modifiedDateTime;
	
	private Date createdDateTime;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private String isActive;
	
	private String year;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public java.sql.Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(java.sql.Date nextDate) {
		this.nextDate = nextDate;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


}
