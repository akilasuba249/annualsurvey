package com.mahindra.annualsurvey.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mt_pacode", schema = "annual_survey")
public class MTPacode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long paId;

	private String paCode;
	
	private String paText;
	
	private String ssoFlag;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pa_businessId")
	private MTBusiness pabusiness;

	public long getPaId() {
		return paId;
	}

	public void setPaId(long paId) {
		this.paId = paId;
	}

	public String getPaCode() {
		return paCode;
	}

	public void setPaCode(String paCode) {
		this.paCode = paCode;
	}

	public String getPaText() {
		return paText;
	}

	public void setPaText(String paText) {
		this.paText = paText;
	}

	public MTBusiness getPabusiness() {
		return pabusiness;
	}

	public void setPabusiness(MTBusiness pabusiness) {
		this.pabusiness = pabusiness;
	}

	public String getSsoFlag() {
		return ssoFlag;
	}

	public void setSsoFlag(String ssoFlag) {
		this.ssoFlag = ssoFlag;
	}

}
