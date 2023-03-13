/**
 * 
 */
package com.mahindra.annualsurvey.dto;

import java.sql.Timestamp;

/**
 * @author Yash Mahale
 *
 */
public class AreaYearMappingDTO {
	
	
		private int pkDetailsId;
		private int areaCode;
		private String year;
		private String createdBy;
		private Timestamp createdDateTime;
		private String modifiedBy;
		private Timestamp modifiedDateTime;
		private String isActive;
		public int getPkDetailsId() {
			return pkDetailsId;
		}
		public void setPkDetailsId(int pkDetailsId) {
			this.pkDetailsId = pkDetailsId;
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
		public Timestamp getCreatedDateTime() {
			return createdDateTime;
		}
		public void setCreatedDateTime(Timestamp createdDateTime) {
			this.createdDateTime = createdDateTime;
		}
		public String getModifiedBy() {
			return modifiedBy;
		}
		public void setModifiedBy(String modifiedBy) {
			this.modifiedBy = modifiedBy;
		}
		public Timestamp getModifiedDateTime() {
			return modifiedDateTime;
		}
		public void setModifiedDateTime(Timestamp modifiedDateTime) {
			this.modifiedDateTime = modifiedDateTime;
		}
		public String getIsActive() {
			return isActive;
		}
		public void setIsActive(String isActive) {
			this.isActive = isActive;
		}
		public int getAreaCode() {
			return areaCode;
		}
		public void setAreaCode(int areaCode) {
			this.areaCode = areaCode;
		}

}
