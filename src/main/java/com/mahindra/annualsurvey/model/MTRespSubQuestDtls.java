package com.mahindra.annualsurvey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mt_resp_subquest_dtls", schema = "annual_survey")
public class MTRespSubQuestDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subquestrespdtl_id")
	private long mtrespsubquestid;

	private long subquestionId;

	@Column(name = "questionbank_id")
	private int questionBankId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subquestrespmapid")
	private MTResponseDetails respdtls;

	@Column(name = "answer_text")
	private String answerText;

	@Column(name = "answer_no")
	private int answerNo;

	public int getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(int questionBankId) {
		this.questionBankId = questionBankId;
	}

	public long getMtrespsubquestid() {
		return mtrespsubquestid;
	}

	public void setMtrespsubquestid(long mtrespsubquestid) {
		this.mtrespsubquestid = mtrespsubquestid;
	}

	public long getSubquestionId() {
		return subquestionId;
	}

	public void setSubquestionId(long subquestionId) {
		this.subquestionId = subquestionId;
	}

	public MTResponseDetails getRespdtls() {
		return respdtls;
	}

	public void setRespdtls(MTResponseDetails respdtls) {
		this.respdtls = respdtls;
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

}
