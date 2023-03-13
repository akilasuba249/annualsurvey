/**
 * 
 */
package com.mahindra.annualsurvey.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yash Mahale
 *
 */
@XmlRootElement(name = "EmployeeMaster")
public class EmployeeMasterDTO {

	
//	private String areaEmployeeID;
	
	private String employeeID;
	
	private String participation;
	
	private String fiscalYear;
	
	private String fullName;
	
	private String employeeAge;
	
	private String areaCode;
	
	private String areaDescription;
	
	private String sectorID;
	
	private String sectorOthers;
	
	private String sector;
	
	private String business;
	
	private String businessOthers;
	
	private String country;
	
	private String gender;
	
	private String dateOfBirth;
	
	private String businessName;
	
	private String startDate;
	
	private String endDate;
	
	/*public String getAreaEmployeeID() {
		return areaEmployeeID;
	}
	@XmlElement
	public void setAreaEmployeeID(String areaEmployeeID) {
		this.areaEmployeeID = areaEmployeeID;
	}*/
	public String getEmployeeID() {
		return employeeID;
	}
	@XmlElement
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getParticipation() {
		return participation;
	}
	@XmlElement
	public void setParticipation(String participation) {
		this.participation = participation;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	@XmlElement
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public String getFullName() {
		return fullName;
	}
	@XmlElement
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	@XmlElement
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaDescription() {
		return areaDescription;
	}
	@XmlElement
	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}
	public String getSectorID() {
		return sectorID;
	}
	@XmlElement
	public void setSectorID(String sectorID) {
		this.sectorID = sectorID;
	}
	@XmlElement
	public String getSectorOthers() {
		return sectorOthers;
	}
	public void setSectorOthers(String sectorOthers) {
		this.sectorOthers = sectorOthers;
	}
	@XmlElement
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	@XmlElement
	public String getBusiness() {
		return business;
	}
	@XmlElement
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getBusinessOthers() {
		return businessOthers;
	}
	@XmlElement
	public void setBusinessOthers(String businessOthers) {
		this.businessOthers = businessOthers;
	}
	public String getCountry() {
		return country;
	}
	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	@XmlElement
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	@XmlElement
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBusinessName() {
		return businessName;
	}
	@XmlElement
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getStartDate() {
		return startDate;
	}
	@XmlElement
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	@XmlElement
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEmployeeAge() {
		return employeeAge;
	}
	public void setEmployeeAge(String employeeAge) {
		this.employeeAge = employeeAge;
	}
	
	
}
