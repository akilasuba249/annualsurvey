package com.mahindra.annualsurvey.dto;

import java.sql.Timestamp;

public class AreaEmpDTO {

	private long pkAreaEmp;

	private String areaCode;
	private String year;
	private String createdBy;
	private Timestamp createdDateTime;
	private String modifiedBy;
	private Timestamp modifiedDateTime;
	private String isActive;
	private long areaCode1;
	
	
	public long getAreaCode1() {
		return areaCode1;
	}

	public void setAreaCode1(long areaCode1) {		
		this.areaCode1 = areaCode1;
	}

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

}
