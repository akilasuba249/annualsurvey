package com.mahindra.annualsurvey.dto;

import java.util.List;

import com.mahindra.annualsurvey.model.MTSubQuestion;


public class QuestionDTO {
    private long questionId;
	
	private String questionDesc;
	
	private int questionType;
	
	private int sequence;
	
	private long areaCode;
	
	private String createdBy;
	
	private String modifiedBy;
	
	private int year;
	
	private String isActive;
	
	private List<MTSubQuestion> subquest;

	public long getQuestionId() {
		return questionId;
	}

	public List<MTSubQuestion> getSubquest() {
		return subquest;
	}

	public void setSubquest(List<MTSubQuestion> subquest) {
		this.subquest = subquest;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(long areaCode) {
		this.areaCode = areaCode;
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

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
