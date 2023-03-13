/**
 * 
 */
package com.mahindra.annualsurvey.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yash Mahale
 *
 */
@XmlRootElement(name="EmployeeMasterList")
public class EmployeeMasterDTOList {
	
	private List<EmployeeMasterDTO> employeeMasterList;

	
	public EmployeeMasterDTOList(List<EmployeeMasterDTO> employeeMasterList) {
		this.employeeMasterList=employeeMasterList;
		
	}
	@javax.xml.bind.annotation.XmlElement(name = "EmployeeMaster")  
	public List<EmployeeMasterDTO> getEmployeeMasterList() {
		return employeeMasterList;
	}

	public void setEmployeeMasterList(List<EmployeeMasterDTO> employeeMasterList) {
		this.employeeMasterList = employeeMasterList;
	}

	
}
