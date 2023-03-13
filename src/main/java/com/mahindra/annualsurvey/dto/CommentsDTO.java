/**
 * 
 */
package com.mahindra.annualsurvey.dto;

/**
 * @author Yash Mahale
 *
 */
public class CommentsDTO {
	

	public String employeeID;
	public String employeeName;
	public String areaDescription;
	public String comments;
	public String year;
	
	
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAreaDescription() {
		return areaDescription;
	}
	public void setAreaDescription(String areaDescription) {
		this.areaDescription = areaDescription;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

}
