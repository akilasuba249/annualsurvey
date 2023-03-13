package com.mahindra.annualsurvey.model;

/**
 * Created on 28th January,2016
 */

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @author Yash Mahale
 * 
 */
@Entity
@Table(name = "mt_employee", schema = "annual_survey")
public class MTEmployee implements Serializable, HttpSessionBindingListener {

	@Id
	@Column(name = "pk_emp")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pkEmp;

	@Column(name = "emp_id")
	private String empId;

	@Column(name = "fname")
	private String fName;

	@Column(name = "lname")
	private String lName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "age")
	private Float age;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "year")
	private String year;

	@Column(name = "sector")
	private Long sector;

	
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

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "area_emp_id")
	private List<AreaEmpMapping> emp;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="pk_emp")
	private List<MTResponse> mtResponse;

	private String usertype;
	
	@Column(name = "password")
	private String password;
	
	private Long business;
	
    private String pa;     

	private String businessOthers;

	private String sectorOthers; 
	
    private String sapFlag;
    
    @Column(name = "user_attempts")
    private String userAttempts;
    


	public String getUserAttempts() {
		return userAttempts;
	}


	public void setUserAttempts(String userAttempts) {
		this.userAttempts = userAttempts;
	}




	public String getSapFlag() {
		return sapFlag;
	}


	public void setSapFlag(String sapFlag) {
		this.sapFlag = sapFlag;
	}


	public String getBusinessOthers() {
		return businessOthers;
	}


	public void setBusinessOthers(String businessOthers) {
		this.businessOthers = businessOthers;
	}


	public String getSectorOthers() {
		return sectorOthers;
	}


	public void setSectorOthers(String sectorOthers) {
		this.sectorOthers = sectorOthers;
	}


	public String getUsertype() {
		return usertype;
	}

	
	public Long getBusiness() {
		return business;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public void setBusiness(Long business) {
		this.business = business;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPkEmp() {
		return pkEmp;
	}

	public void setPkEmp(long pkEmp) {
		this.pkEmp = pkEmp;
	}

	@Column(name = "emp_id")
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String d) {

		this.empId = d;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}

	public List<AreaEmpMapping> getEmp() {
		return emp;
	}

	public void setEmp(List<AreaEmpMapping> emp) {
		this.emp = emp;
	}

	public Long getSector() {
		return sector;
	}

	public void setSector(Long sector) {
		this.sector = sector;
	}
	
	@Override
	public String toString() {

		return "{\"empId\":\""
				+ getEmpId()
				+ "\",\"email\":\""
				+ getEmailId()
				+ "\",\"city\":\""
				+ getCity()
				+ "\",\"country\":\""
				+ getCountry()
				+ "\",\"datofBirth\":\""
				+ getDateOfBirth()
				+ "\",\"sector\":\""
				+ getSector()
				+ "\",\"business\":\""
				+ getBusiness()				
				+ "\",\"sectorOthers\":\""
				+ getSectorOthers()
				+ "\",\"businessOthers\":\""
				+ getBusinessOthers()							
				+ "\",\"gender\":\""
				+ getGender()
				+ "\",\"pkEmp\":\""
				+ getPkEmp()
				+ "\",\"year\":\""
				+ getYear()
				+ "\",\"fname\":\""
				+ getfName()
				+ "\","
				+ "\"lname\":\""
				+ getlName()
				+ "\",\"industries\":\""
				+ new com.mahindra.annualsurvey.dao.ObjectSerializer()
						.getAreaCodeNames(getEmp(), ",") + "\"}";
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<MTResponse> getMtResponse() {
		return mtResponse;
	}

	public void setMtResponse(List<MTResponse> mtResponse) {
		this.mtResponse = mtResponse;
	}

	/** Logic for session management / for concurrent session handling **/
	private static Map<MTEmployee, HttpSession> logins = new HashMap<MTEmployee, HttpSession>();
	
	@Override
	public boolean equals(Object other) {
		return (other instanceof MTEmployee) && (getEmpId() != null) ? getEmpId().equals(((MTEmployee) other).getEmpId()) : (other == this);
	}

	@Override
	public int hashCode() {
		return (getEmpId() != null) ? (this.getClass().hashCode() + getEmpId().hashCode()) : super.hashCode();
	}
	
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession oldSession = logins.remove(this);
		if (oldSession != null) {
		//	oldSession.invalidate();
		}
		System.out.println("In value Bond");
		logins.put(this, event.getSession());
		// Note: you can comment above code and remove comments from below code.
		// removing comments from
		// below code will remove old session of user and let the user log-in
		// from new session.

		// HttpSession session = logins.remove(this);
		// if (session != null) {
		// session.invalidate();
		// }
		// logins.put(this, event.getSession());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {

		System.out.println("In value UnBond");
		//logins.remove(this);
	}
	
	
}
