/**
 * 
 */
package com.mahindra.annualsurvey.dto;

import java.sql.Date;


/**
 * @author Yash Mahale
 *
 */
public class YearDTO {
	
	
	private int pkYear;

	private String Year;
		
	private Date fromDate;
	
	private Date toDate;

	public int getPkYear() {
		return pkYear;
	}

	public void setPkYear(int pkYear) {
		this.pkYear = pkYear;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
