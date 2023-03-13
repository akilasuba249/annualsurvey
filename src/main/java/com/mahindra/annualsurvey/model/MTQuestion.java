package com.mahindra.annualsurvey.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "mt_question", schema = "annual_survey")
public class MTQuestion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private long questionId;
	
	@Column(name = "question_desc",length = 1500)
	private String questionDesc;
	
/*	@Column(name = "question_type")
	private String questionType;*/
	
	@Column(name = "sequence")
	private int sequence;
	
/*	@Column(name = "area_code")
	private int areaCode;
*/
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

	@ManyToOne/*(fetch=FetchType.LAZY)*/
    @JoinColumn(name="area_code")
	private MTArea questionArea;


	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pk_question_type")
	private MTQuestionType questType;


	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pk_year")
	private MTYear yearMap;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subquestionmapid")
    private List<MTSubQuestion> mtsubquestion;
	
	public List<MTSubQuestion> getMtsubquestion() {
		return mtsubquestion;
	}

	public void setMtsubquestion(List<MTSubQuestion> mtsubquestion) {
		this.mtsubquestion = mtsubquestion;
	}

	public MTQuestionType getQuestType() {
		return questType;
	}

	public void setQuestType(MTQuestionType questType) {
		this.questType = questType;
	}


		public long getQuestionId() {
		return questionId;
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

	/*public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}*/

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp timestamp) {
		this.createdDateTime = timestamp;
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

	public MTArea getQuestionArea() {
		return questionArea;
	}

	public void setQuestionArea(MTArea questionArea) {
		this.questionArea = questionArea;
	}

	public MTYear getYearMap() {
		return yearMap;
	}

	public void setYearMap(MTYear yearMap) {
		this.yearMap = yearMap;
	}

	@Override
	public String toString() {

		return "{\"questionId\":\""
				+ getQuestionId()
				+ "\",\"questionDesc\":\""
				+ getQuestionDesc()
				+ "\",\"questionArea\":\""
				+  getQuestionArea().getAreaDesc()
				+ "\",\"year\":\""
				+  getYearMap().getYear()
				+ "\",\"areaCode\":\""
				+  getQuestionArea().getAreaCode()
				+ "\",\"pkYear\":\""
				+  getYearMap().getPkYear()
			
					+ "\",\"pkQuestType\":\""
				+  getQuestType().getPkQuestType()
			
				+ "\",\"questionType\":\""
				+  getQuestType().getQuestTypeDesc() + "\"}";
				
			}
}
