package com.mahindra.annualsurvey.model;

import javax.persistence.Entity;
	import java.io.Serializable;
	import java.util.Date;
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name = "mt_loginhistory")
	public class MTLoginHistory implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long mtLoginHistoryID;
		private String ipAddress;
		private Date loginOn;
		private String loginUser;
		private String browserName;
		private String browserVersion;
		
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "mt_loginhistory_id", updatable = false)
		public Long getMtLoginHistoryID() {
			return mtLoginHistoryID;
		}
		public void setMtLoginHistoryID(Long mtLoginHistoryID) {
			this.mtLoginHistoryID = mtLoginHistoryID;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public Date getLoginOn() {
			return loginOn;
		}
		public void setLoginOn(Date loginOn) {
			this.loginOn = loginOn;
		}
		public String getLoginUser() {
			return loginUser;
		}
		public void setLoginUser(String loginUser) {
			this.loginUser = loginUser;
		}
		public String getBrowserName() {
			return browserName;
		}
		public void setBrowserName(String browserName) {
			this.browserName = browserName;
		}
		public String getBrowserVersion() {
			return browserVersion;
		}
		public void setBrowserVersion(String browserVersion) {
			this.browserVersion = browserVersion;
		}
		
		
	}


