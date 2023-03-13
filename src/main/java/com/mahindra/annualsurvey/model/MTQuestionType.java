package com.mahindra.annualsurvey.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mt_question_type", schema = "annual_survey")
public class MTQuestionType  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pk_question_type")
	private int pkQuestType;

	@Column(name = "question_type_desc")
	private String questTypeDesc;
	
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

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="pk_question_type")
	private Set<MTQuestion> questTypeCode;
	
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


	public Set<MTQuestion> getQuestTypeCode() {
		return questTypeCode;
	}

	public void setQuestTypeCode(Set<MTQuestion> questTypeCode) {
		this.questTypeCode = questTypeCode;
	}

	public String getQuestTypeDesc() {
		return questTypeDesc;
	}

	public void setQuestTypeDesc(String questTypeDesc) {
		this.questTypeDesc = questTypeDesc;
	}

}
