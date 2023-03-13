/**
 * 
 */
package com.mahindra.annualsurvey.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Yash Mahale
 *
 */

@Entity
@Table(name="mt_year",schema="annual_survey")
public class MTYear implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pk_year")
	private int pkYear;
	
	@Column(name="year")
	private String year;
		
	@Column(name="from_date")
	private Date fromDate;
	
	@Column(name="to_date")
	private Date toDate;
	

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="pk_year")
	private Set<MTQuestion>yearMap;

	public int getPkYear() {
		return pkYear;
	}

	public void setPkYear(int pkYear) {
		this.pkYear = pkYear;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
		
	public Set<MTQuestion> getYearMap() {
		return yearMap;
	}

	public void setYearMap(Set<MTQuestion> yearMap) {
		this.yearMap = yearMap;
	}

}
