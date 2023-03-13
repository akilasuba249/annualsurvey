package com.mahindra.annualsurvey.model;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "mt_business", schema = "annual_survey")
public class MTBusiness {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long businessId;

	private String BusinessName;
	
	private String BusinessCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sectorBusinessId")
	private MTSector mtsector;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "pa_businessId")
	private List<MTPacode> mtpa;
	
	public MTSector getMtsector() {
		return mtsector;
	}

	public void setMtsector(MTSector mtsector) {
		this.mtsector = mtsector;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getBusinessCode() {
		return BusinessCode;
	}

	public void setBusinessCode(String businessCode) {
		BusinessCode = businessCode;
	}
	
	@Override
	public String toString() {

		return "{\"businessId\":\""
				+ getBusinessId()				
				+ "\",\"businessName\":\""
				+ getBusinessName() + "\"}";
	}

	
	
}
