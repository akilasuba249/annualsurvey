/**
 * 
 */
package com.mahindra.annualsurvey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Yash Mahale			
 *
 */
@Entity
@Table(name="mt_mail", schema="annual_survey")
public class MTMail {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mt_mail_id;
	
	@Column(name="year")
	private String year;
	
	@Column(name="mail_type")
	private String mailType;
	
	@Column(name="mail_subject")
	private String mailSubject;
	
	@Column(name="mail_body")
	private String mailBody;
	
	@Column(name="salutation")
	private String salutation;
	
	@Column(name="closing")
	private String closing;
	
	@Column(name="signature")
	private String signature;
	
	@Column(name="non_mahindra")
	private String nonMahindra;

	public int getMt_mail_id() {
		return mt_mail_id;
	}

	public void setMt_mail_id(int mt_mail_id) {
		this.mt_mail_id = mt_mail_id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getClosing() {
		return closing;
	}

	public void setClosing(String closing) {
		this.closing = closing;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNonMahindra() {
		return nonMahindra;
	}

	public void setNonMahindra(String nonMahindra) {
		this.nonMahindra = nonMahindra;
	}
	
	
	
	
	

}
