package com.mahindra.annualsurvey.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="mt_response",schema="annual_survey")
public class MTResponse implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="response_id")
	private long responseId;

	@Column(name="survey_id")
	private int surveyId;

	@Column(name="entry_datetime")
	private Date entryDateTime;

	@Column(name="sector_id")
	private int sectorId;

	@Column(name="status")
	private String status;

	@Column(name="lastupdated_datetime")
	private Date lastUpdatedDateTime;

	@Column(name="is_active")
	private String isActive;

	@Column(name="year")
	private String year;
	
	private String operation; //added by nikita
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "response_map_id")
	private List<MTResponseDetails> tblresponsedtls;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pk_emp")
	private MTEmployee mtEmployee;
	
	public long getResponseId() {
		return responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}


	public Date getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(Date entryDateTime) {
		this.entryDateTime = entryDateTime;
	}

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}


	public Date getLastUpdatedDateTime() {
		return lastUpdatedDateTime;
	}

	public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<MTResponseDetails> getTblresponsedtls() {
		return tblresponsedtls;
	}

	public void setTblresponsedtls(List<MTResponseDetails> tblresponsedtls) {
		this.tblresponsedtls = tblresponsedtls;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MTEmployee getMtEmployee() {
		return mtEmployee;
	}

	public void setMtEmployee(MTEmployee mtEmployee) {
		this.mtEmployee = mtEmployee;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}


	
}
