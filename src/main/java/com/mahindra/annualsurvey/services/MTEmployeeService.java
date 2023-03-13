/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTLoginHistory;

/**
 * @author ajay.dubey
 * 
 */
public interface MTEmployeeService {
	public String create(EmployeeDTO emp);

	public String update(EmployeeDTO emp);

	void deletebyId(long l, String year);

	public long getArea(String areaDesc);

	public void delete(long entityId);

	public List<MTEmployee> get(String id);

	public List<MTEmployee> getEmployeeList();

	public List<MTEmployee> getEmployeeListYear(String year);

	List<MTEmployee> getEmployeeListYearArea(String area, String year);

	public List<Object> getEmployeeYear(String year, String empId, String choose);

	public String updateEmp(EmployeeDTO emp);

	public boolean sapEmployeeInt();

	public void updateEmpSAPDetails(MTEmployee updatedEmpDetails);

//	public List<MTEmployee> checkLogin(MTEmployee user);
    
	public boolean sapEmployeeIntCreate(String empId);
    
	public MTEmployee checkLoginExternal(String username, String password);
	
	public MTEmployee getuserdetails(String username, String tokenid);
     
	public void updateEmpSAPDetailsUser(MTEmployee updatedEmpDetails);
	
	List<MTArea> getallarea();
	
//	public Boolean createemp(MTEmployee emp);

	public Boolean updateemp(MTEmployee emp);
	
	public Boolean findareamapping(String area_code,String area_emp_id ,String year);
	
	public Boolean insertareamapping(String area_code,String areaDesc,String area_emp_id ,String year);
	
	List<AreaDTO> getallcurryearactivearea();

	public boolean firstTimeLogin(MTEmployee mtLogin);



	
	
	
	
	
}
