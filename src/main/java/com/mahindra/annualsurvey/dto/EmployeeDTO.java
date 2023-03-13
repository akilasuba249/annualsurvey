package com.mahindra.annualsurvey.dto;

import java.util.List;

public class EmployeeDTO {

	private long pkEmp;

	private String empId;

	private String fName;

	private String lName;

	private String gender;

	private String dateOfBirth;

	private String country;

	private String city;

	private Float age;

	private String emailId;

	private String year;

	private String createdBy;

	private String modifiedBy;

	private List<AreaEmpDTO> Area;
	
	private Long sector;
	
	private Long business;
	
	
	private String status;
	
	private String fullName;

	private String businessOthers;

	private String sectorOthers;
	
	private String sapFlag; 
	
	
	private String password;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBusinessOthers() {
		return businessOthers;
	}

	public void setBusinessOthers(String businessOthers) {
		this.businessOthers = businessOthers;
	}

	public String getSectorOthers() {
		return sectorOthers;
	}

	public void setSectorOthers(String sectorOthers) {
		this.sectorOthers = sectorOthers;
	}

	public long getPkEmp() {
		return pkEmp;
	}

	public List<AreaEmpDTO> getArea() {
		return Area;
	}

	public void setArea(List<AreaEmpDTO> area) {
		Area = area;
	}

	public void setPkEmp(long pkEmp) {
		this.pkEmp = pkEmp;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String d) {
		this.empId = d;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getSector() {
		return sector;
	}

	public void setSector(Long sector) {
		this.sector = sector;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getBusiness() {
		return business;
	}

	public void setBusiness(Long business) {
		this.business = business;
	}

	public String getSapFlag() {
		return sapFlag;
	}

	public void setSapFlag(String sapFlag) {
		this.sapFlag = sapFlag;
	}

}
