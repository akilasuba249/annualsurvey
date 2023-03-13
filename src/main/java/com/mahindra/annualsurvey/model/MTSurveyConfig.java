package com.mahindra.annualsurvey.model;
/** Created on 17th feb,2016*/
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Digvijay Singh
 * 
 */
@Entity
@Table(name = "mt_survey_config", schema = "annual_survey")
public class MTSurveyConfig implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "s_no")
	private int sno;
	
	@Column(name = "year")
	private String year;
	
	
	@Column(name = "start_date")
	private String startDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;
	
	@Column(name = "frequency_loop")
	private int frequencyLoop;
	
	@Column(name = "next_date")
	private String nextDate;

	/*private List<MTSurveyConfig> CreateSurvey;
	

	public List<MTSurveyConfig> getCreateSurvey() {
		return CreateSurvey;
	}
	public void setCreateSurvey(List<MTSurveyConfig> createSurvey) {
		CreateSurvey = createSurvey;
	}*/
	
	public int getSno() {
		return sno;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getFrequencyLoop() {
		return frequencyLoop;
	}
	public void setFrequencyLoop(int frequencyLoop) {
		this.frequencyLoop = frequencyLoop;
	}

	public String getNextDate() {
		return nextDate;
	}
	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}	 

}
