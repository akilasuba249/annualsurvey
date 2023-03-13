/**
 * 
 */
package com.mahindra.annualsurvey.dto;

/**
 * @author Yash Mahale
 *
 */
public class ResponseMasterDTO {

	
	private String employeeID;
	private String areaCode;
	private String questionID;
	private String questionType;
	private String subQuestionID;
	private String answer;
	private String respondedDate;
	private String year;
	private String status;
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getSubQuestionID() {
		return subQuestionID;
	}
	public void setSubQuestionID(String subQuestionID) {
		this.subQuestionID = subQuestionID;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRespondedDate() {
		return respondedDate;
	}
	public void setRespondedDate(String respondedDate) {
		this.respondedDate = respondedDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
