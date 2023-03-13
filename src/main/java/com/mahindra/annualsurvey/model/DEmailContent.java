package com.mahindra.annualsurvey.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="emailcontent", schema="msquare")
public class DEmailContent implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dEmailContentId;
	private String emailCode;
	private String emailContent;
	
	@Id
	@Column(name="demailcontentid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getdEmailContentId() {
		return dEmailContentId;
	}
	public void setdEmailContentId(int dEmailContentId) {
		this.dEmailContentId = dEmailContentId;
	}
	@Column(name="emailcode")
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	@Column(name="emailcontent")
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
}
