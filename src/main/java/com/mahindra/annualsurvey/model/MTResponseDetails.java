package com.mahindra.annualsurvey.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mahindra.annualsurvey.dao.ObjectSerializer;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "mt_response_dtls", schema = "annual_survey")
public class MTResponseDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "resposedetail_id")
	private int responseDetailId;

	@Column(name = "questionbank_id")
	private int questionBankId;

	@Column(name = "answer_text", length = 1000000)
	@Lob
	private String answerText;

	@Column(name = "answer_no")
	private int answerNo;

	@Column(name = "responded_datetime")
	private Date respondedDateTime;

	@Column(name = "update_datetime")
	private Date updatedDateTime;

	@Column(name = "responded_by")
	private String RespondedBy;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "is_active")
	private String isActive;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="area_code")
	private MTArea questionArea;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "response_map_id")
	private MTResponse tblresponse;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "subquestrespmapid")
	private List<MTRespSubQuestDtls> mtrespsub;

	private String questionType;

	@Column(name = "comments", length = 1000000)
	@Lob
	private String comments;
	
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public int getResponseDetailId() {
		return responseDetailId;
	}

	public void setResponseDetailId(int responseDetailId) {
		this.responseDetailId = responseDetailId;
	}

	public int getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(int questionBankId) {
		this.questionBankId = questionBankId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public int getAnswerNo() {
		return answerNo;
	}

	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}

	public Date getRespondedDateTime() {
		return respondedDateTime;
	}

	public void setRespondedDateTime(Date respondedDateTime) {
		this.respondedDateTime = respondedDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getRespondedBy() {
		return RespondedBy;
	}

	public void setRespondedBy(String respondedBy) {
		RespondedBy = respondedBy;
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

	public MTResponse getTblresponse() {
		return tblresponse;
	}

	public void setTblresponse(MTResponse tblresponse) {
		this.tblresponse = tblresponse;
	}

	public List<MTRespSubQuestDtls> getMtrespsub() {
		return mtrespsub;
	}

	public void setMtrespsub(List<MTRespSubQuestDtls> mtrespsub) {
		this.mtrespsub = mtrespsub;
	}

	public MTArea getQuestionArea() {
		return questionArea;
	}

	public void setQuestionArea(MTArea questionArea) {
		this.questionArea = questionArea;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {

		return "{\"questionBankId\":\"" + getQuestionBankId()
				+ "\",\"answerNo\":\"" + getAnswerNo() + "\",\"status\":\""
				+ getTblresponse().getStatus() + "\",\"responsemapID\":\""
				+ getTblresponse().getResponseId() + "\",\"areacode\":\""
				+ getQuestionArea().getAreaCode() + "\",\"comments\":\""			
		        + getComments() + "\",\"subquestionmap\":\""			
				+ new ObjectSerializer().getSubquestRespNames(getMtrespsub(), ",") + "\",\"questionType\":\""
				+ getQuestionType() + "\",\"answerText\":\"" + getAnswerText()
				+ "\"}";
	}

}
