package com.mahindra.annualsurvey.model;

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

import org.apache.commons.io.output.TeeOutputStream;

@Entity
@Table(name = "mt_sub_question", schema = "annual_survey")
public class MTSubQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_question_id")
	private int subQuestionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subquestionmapid")
	private MTQuestion mtQuestion; 
	
	public MTQuestion getMtQuestion() {
		return mtQuestion;
	}

	public void setMtQuestion(MTQuestion mtQuestion) {
		this.mtQuestion = mtQuestion;
	}

	@Column(name = "sub_question_desc")
	private String subQuestDesc;
	
	@Column(name = "question_type")
	private String questionType;
	
	@Column(name = "modified_datetime")
	private Timestamp modifiedDateTime;

	@Column(name = "created_datetime")
	private Timestamp createdDateTime;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "is_active")
	private String isActive;

	public int getSubQuestionId() {
		return subQuestionId;
	}

	public void setSubQuestionId(int subQuestionId) {
		this.subQuestionId = subQuestionId;
	}

	
	public String getSubQuestDesc() {
		return subQuestDesc;
	}

	public void setSubQuestDesc(String subQuestDesc) {
		this.subQuestDesc = subQuestDesc;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
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
       
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "{\"subQuestionId\":\""
		+ getSubQuestionId()
		+ "\",\"questionId\":\""
		+ getMtQuestion().getQuestionId()
		+ "\",\"subQuestDesc\":\""
		+  getSubQuestDesc() + "\"}";
		
	}
	
}
