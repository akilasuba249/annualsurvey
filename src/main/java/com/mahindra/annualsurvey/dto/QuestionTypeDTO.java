package com.mahindra.annualsurvey.dto;

import java.sql.Timestamp;

public class QuestionTypeDTO {

	private int pkQuestType;
	
	private String questTypeDesc;
	
	private String createdBy;
	
	private Timestamp createdDateTime;

	private String modifiedBy;

	private Timestamp modifiedDateTime;

	private String isActive;

	public int getPkQuestType() {
		return pkQuestType;
	}

	public void setPkQuestType(int pkQuestType) {
		this.pkQuestType = pkQuestType;
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

	public String getQuestTypeDesc() {
		return questTypeDesc;
	}

	public void setQuestTypeDesc(String questTypeDesc) {
		this.questTypeDesc = questTypeDesc;
	}

}
