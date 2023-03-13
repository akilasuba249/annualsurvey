package com.mahindra.annualsurvey.dto;



/**
 * 
 */

/**
 * @author Yash Mahale
 *
 */
public class MailDTO {

	
	
	private int mt_mail_id;
	
	private String year;
	
	private String mailType;
	
	private String mailSubject;
	
	private String mailBody;
	
	private String salutation;
	
	private String closing;
	
	private String signature;
	
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
