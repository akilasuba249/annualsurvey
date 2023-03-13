package com.mahindra.annualsurvey.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "mt_area", schema = "annual_survey")
public class MTArea implements Serializable {
	private long areaCode;
	private String areaDesc;
	private String createdBy;
	private Timestamp createdDateTime;
	private String modifiedBy;
	private Timestamp ModifiedDateTime;
	private String isActive;
	@Column(name = "area_order")
	private Long area_order;

	private Set<AreaYearMapping> areaYearMapping;

	private Set<MTQuestion> areaQuestion;

	private Set<MTEmployee> areaemployee;

	private Set<MTResponseDetails> arearesponsedtl;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_code")
	public Set<MTResponseDetails> getArearesponsedtl() {
		return arearesponsedtl;
	}

	public void setArearesponsedtl(Set<MTResponseDetails> arearesponsedtl) {
		this.arearesponsedtl = arearesponsedtl;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_code")
	public Set<MTEmployee> getAreaemployee() {
		return areaemployee;
	}

	public void setAreaemployee(Set<MTEmployee> areaemployee) {
		this.areaemployee = areaemployee;
	}

	@Id
	@Column(name = "area_code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(long areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "area_desc")
	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_datetime")
	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "modified_datetime")
	public Timestamp getModifiedDateTime() {
		return ModifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		ModifiedDateTime = modifiedDateTime;
	}

	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_code")
	public Set<AreaYearMapping> getAreaYearMapping() {
		return areaYearMapping;
	}

	public void setAreaYearMapping(Set<AreaYearMapping> areaYearMapping) {
		this.areaYearMapping = areaYearMapping;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_code")
	public Set<MTQuestion> getAreaQuestion() {
		return areaQuestion;
	}

	public void setAreaQuestion(Set<MTQuestion> areaQuestion) {
		this.areaQuestion = areaQuestion;
	}

	public Long getArea_order() {
		return area_order;
	}

	public void setArea_order(Long area_order) {
		this.area_order = area_order;
	}

	

}
