package com.mahindra.annualsurvey.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mt_sector", schema = "annual_survey")
public class MTSector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sectorId;
	
	private String SectorName;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "sectorBusinessId")
	private List<MTBusiness> mtbusiness;

	public long getSectorId() {
		return sectorId;
	}

	public void setSectorId(long sectorId) {
		this.sectorId = sectorId;
	}

	public String getSectorName() {
		return SectorName;
	}

	public void setSectorName(String sectorName) {
		SectorName = sectorName;
	}

	public List<MTBusiness> getMtbusiness() {
		return mtbusiness;
	}

	public void setMtbusiness(List<MTBusiness> mtbusiness) {
		this.mtbusiness = mtbusiness;
	}
	 
}
