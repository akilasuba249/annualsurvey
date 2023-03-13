package com.mahindra.annualsurvey.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
@Entity
@Table(name="mt_role",schema="annual_survey")
public class MTRole 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private String Empid;
	@Column(name="emp_role")
	private String EmpRole;
	
	@Column(name="modified_datetime")
	private Date modifiedDateTime;
	
	@Column(name="create_datetime")
	private Date createdDateTime;
	
	@Column(name="create_by")
	private String createdBy;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Column(name="is_active")
	private String isActive;
	public String getEmpid() {
		return Empid;
	}

	public void setEmpid(String empid) {
		Empid = empid;
	}

	public String getEmpRole() {
		return EmpRole;
	}

	public void setEmpRole(String empRole) {
		EmpRole = empRole;
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

	

}
