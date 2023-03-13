/**
 * 
 */
package com.mahindra.annualsurvey.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Yash Mahale
 * 
 */

@Entity
@Table(name = "area_emp_mapping")
public class AreaEmpMapping implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_area_emp")
	private long pkAreaEmp;
	
	
	public MTArea getEmployeeArea() {
		return employeeArea;
	}

	public void setEmployeeArea(MTArea employeeArea) {
		this.employeeArea = employeeArea;
	}

	@Column(name = "areaDesc")
	private String areaCode;
	@Column(name = "year")
	private String year;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "created_datetime")
	private Timestamp createdDateTime;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "modified_datetime")
	private Timestamp modifiedDateTime;
	@Column(name = "is_active")
	private String isActive;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_emp_id")
 	private MTEmployee mtemployee; 

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="area_code")
	private MTArea employeeArea;


	
	public long getPkAreaEmp() {
		return pkAreaEmp;
	}

	public void setPkAreaEmp(long pkAreaEmp) {
		this.pkAreaEmp = pkAreaEmp;
	}


	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
	public MTEmployee getMtemployee() {
		return mtemployee;
	}

	public void setMtemployee(MTEmployee mtemployee) {
		this.mtemployee = mtemployee;
	}

}
