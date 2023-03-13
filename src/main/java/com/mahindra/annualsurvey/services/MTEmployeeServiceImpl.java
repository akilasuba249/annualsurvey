package com.mahindra.annualsurvey.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mahindra.annualsurvey.dao.AreaEmpMappingDao;
import com.mahindra.annualsurvey.dao.AreaYearMapDAO;
import com.mahindra.annualsurvey.dao.MTAreaDao;
import com.mahindra.annualsurvey.dao.MTEmployeeDAO;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.AreaEmpDTO;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.AreaEmpMapping;
import com.mahindra.annualsurvey.model.MTArea;
import com.mahindra.annualsurvey.model.MTBusiness;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTPacode;
import com.mahindra.annualsurvey.model.MTSector;
import com.mahindra.annualsurvey.utils.PasswordGenSSOdomainCheck;
import com.mahindra.annualsurvey.utils.SAPWebService;

@Service
public class MTEmployeeServiceImpl implements MTEmployeeService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private MTEmployeeDAO employeedao;

	@Autowired
	private MTAreaDao mtareadao;
	
	@Autowired
	private AreaEmpMappingDao areaEmpMappingDao;
	
	@Autowired
	private AreaYearMapDAO areaYearMapDAO;

	@Autowired
	private PasswordGenSSOdomainCheck passwordGenSSOdomainCheck;

	static final Logger logger = Logger.getLogger(MTEmployeeServiceImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String create(EmployeeDTO emp) {
		MTEmployee employee = new MTEmployee();
		employee.setAge(emp.getAge());
		employee.setCity(emp.getCity());
		employee.setCountry(emp.getCountry());
		employee.setCreatedBy("admin");
		employee.setDateOfBirth(emp.getDateOfBirth());
		employee.setEmailId(emp.getEmailId());
		employee.setfName(emp.getfName());
		employee.setGender(emp.getGender());
		employee.setIsActive("Y");
		employee.setlName(emp.getlName());
		employee.setSapFlag("N");
		employee.setYear(emp.getYear());
		employee.setEmpId(emp.getEmpId());
		employee.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		employee.setUsertype("USER");
		employee.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
		
	//	employee.setPassword("mahindra");

		
		List<AreaEmpMapping> n = new ArrayList<AreaEmpMapping>();
		List<AreaEmpDTO> areaemp = emp.getArea();
		for (Iterator iterator = areaemp.iterator(); iterator.hasNext();) {
			AreaEmpDTO areaEmpDTO = (AreaEmpDTO) iterator.next();
			if (!(areaEmpDTO.getAreaCode() == null || areaEmpDTO.getAreaCode() == "")) {
				AreaEmpMapping areaemp1 = new AreaEmpMapping();
				areaemp1.setAreaCode(areaEmpDTO.getAreaCode());
				areaemp1.setCreatedBy("admin");
				areaemp1.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
				areaemp1.setIsActive("Y");
				areaemp1.setModifiedBy(areaEmpDTO.getModifiedBy());

				areaemp1.setPkAreaEmp(areaEmpDTO.getPkAreaEmp());
				areaemp1.setYear(emp.getYear());
				MTArea m = new MTArea();
				m.setAreaCode(areaEmpDTO.getAreaCode1());
				areaemp1.setEmployeeArea(m);

				n.add(areaemp1);
			}
		}

		employee.setEmp(n);
		employeedao.create(employee);

		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String update(EmployeeDTO emp) {
		MTEmployee employee = new MTEmployee();
		employee.setAge(emp.getAge());
		employee.setCity(emp.getCity());
		employee.setCountry(emp.getCountry());
		employee.setCreatedBy("admin");
		employee.setModifiedBy("admin");
		employee.setDateOfBirth(emp.getDateOfBirth());
		employee.setEmailId(emp.getEmailId());
		employee.setfName(emp.getfName());
		employee.setGender(emp.getGender());
		employee.setIsActive("Y");
		if (!("Y".equalsIgnoreCase(employee.getSapFlag()))) {
			employee.setSapFlag("N");
		}
		employee.setlName(emp.getlName());
		employee.setYear(emp.getYear());
		employee.setPkEmp(emp.getPkEmp());
		employee.setEmpId(emp.getEmpId());
		employee.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
		employee.setUsertype("USER");// update employee issue edited by ajay sir
		employee.setPassword(employee.getPassword());// update employee issue edited by ajay sir
		employee.setSapFlag(emp.getSapFlag()); //update employee issue edited by ajay sir
		List<AreaEmpMapping> n = new ArrayList<AreaEmpMapping>();
		List<AreaEmpDTO> areaemp = emp.getArea();
		for (Iterator iterator = areaemp.iterator(); iterator.hasNext();) {
			AreaEmpDTO areaEmpDTO = (AreaEmpDTO) iterator.next();
			if (!(areaEmpDTO.getAreaCode() == null || areaEmpDTO.getAreaCode() == "")) {
				AreaEmpMapping areaemp1 = new AreaEmpMapping();
				areaemp1.setAreaCode(areaEmpDTO.getAreaCode());
				MTArea m = new MTArea();
				//
				m.setAreaCode(getArea(areaEmpDTO.getAreaCode()));
				areaemp1.setEmployeeArea(m);
				areaemp1.setCreatedBy("admin");
				areaemp1.setCreatedDateTime(areaEmpDTO.getCreatedDateTime());
				areaemp1.setIsActive(areaEmpDTO.getIsActive());
				areaemp1.setModifiedBy("admin");
				areaemp1.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
				areaemp1.setMtemployee(employee);
				areaemp1.setPkAreaEmp(areaEmpDTO.getPkAreaEmp());
				areaemp1.setYear(areaEmpDTO.getYear());
				n.add(areaemp1);
			}
		}
		employee.setEmp(n);
		// changed by harshad
		MTEmployee newemp = employeedao.findOne(emp.getPkEmp());
		employee.setMtResponse(newemp.getMtResponse());
		employee.setAge(getAge(newemp.getDateOfBirth()));
		employee.setCity(newemp.getCity());
		employee.setCountry(newemp.getCountry());
		employee.setCreatedBy("admin");
		employee.setModifiedBy("admin");
		employee.setDateOfBirth(newemp.getDateOfBirth());

		employeedao.update(employee);
		return null;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String updateEmp(EmployeeDTO emp) {

		entityManager.createQuery(" delete from AreaEmpMapping a where year=" + emp.getYear()
				+ " And a.mtemployee.pkEmp =" + emp.getPkEmp()).executeUpdate();

		MTEmployee employee = employeedao.findOne(emp.getPkEmp());

		employee.setModifiedBy("admin");
		employee.setEmailId(emp.getEmailId());
		employee.setIsActive("Y");
		employee.setYear(emp.getYear());
		employee.setPkEmp(emp.getPkEmp());
		employee.setEmpId(emp.getEmpId());
		employee.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
		employee.setUsertype("USER");
		if(null != emp.getPassword())
		employee.setPassword(emp.getPassword());
		else
			employee.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
		employee.setSapFlag(emp.getSapFlag());
	
		employee.setSapFlag("N");
		List<AreaEmpMapping> n = new ArrayList<AreaEmpMapping>();
		List<AreaEmpDTO> areaemp = emp.getArea();
		for (Iterator iterator = areaemp.iterator(); iterator.hasNext();) {
			AreaEmpDTO areaEmpDTO = (AreaEmpDTO) iterator.next();
			if (!(areaEmpDTO.getAreaCode() == null || areaEmpDTO.getAreaCode() == "")) {
				AreaEmpMapping areaemp1 = new AreaEmpMapping();
				areaemp1.setAreaCode(areaEmpDTO.getAreaCode());
				areaemp1.setCreatedBy("admin");
				areaemp1.setCreatedDateTime(areaEmpDTO.getCreatedDateTime());
				areaemp1.setIsActive("Y");
				areaemp1.setModifiedBy("admin");
				areaemp1.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
				MTArea m = new MTArea();
				m.setAreaCode(areaEmpDTO.getAreaCode1());
				areaemp1.setEmployeeArea(m);
				areaemp1.setMtemployee(employee);
				areaemp1.setPkAreaEmp(areaEmpDTO.getPkAreaEmp());
				areaemp1.setYear(emp.getYear());
				n.add(areaemp1);

			}
		}
		employee.setEmp(n);

		MTEmployee newemp = employeedao.findOne(emp.getPkEmp());
		employee.setMtResponse(newemp.getMtResponse());
		if (null != newemp.getDateOfBirth()) {
			employee.setAge(getAge(newemp.getDateOfBirth()));
		}
		employee.setCity(newemp.getCity());
		employee.setCountry(newemp.getCountry());
		employee.setCreatedBy("admin");
		employee.setModifiedBy("admin");
		employee.setDateOfBirth(newemp.getDateOfBirth());

		employeedao.update(employee);
		return null;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deletebyId(long entityId, String year) {

		entityManager
				.createQuery(
						" delete from AreaEmpMapping a where year=" + year + " And a.mtemployee.pkEmp =" + entityId)
				.executeUpdate();

		entityManager.createQuery("update MTEmployee set isActive='N' where pkEmp=" + entityId).executeUpdate();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long entityId) {
		// TODO Auto-generated method stub
		final AreaEmpMapping entity = entityManager.find(AreaEmpMapping.class, entityId);
		entityManager.remove(entity);

	}

	@Override
	public List<MTEmployee> get(String id) {
		List<MTEmployee> employee = new ArrayList<MTEmployee>();
		employee = employeedao.findAll("emp_id ='" + id + "'");
	
		return employee;
	}

	public List<MTEmployee> getEmployeeList() {

		return employeedao.findAll("isActive='Y' ");
	}

	public List<MTEmployee> getEmployeeListYear(String year) {
		StringBuffer query = new StringBuffer(" select distinct do from MTEmployee do LEFT JOIN FETCH do.emp emp1");
		StringBuffer whereClause = new StringBuffer(" WHERE  do.usertype='USER' ");
		whereClause.append(" AND emp1.year = " + year + " order by do.fName asc"); // chages
																					// for
																					// order
																					// by

		query = query.append(whereClause.toString());

		List<MTEmployee> emp = entityManager.createQuery(query.toString()).getResultList();
		System.out.println(emp);
		return emp;
	}

	public long getArea(String areaDesc) {
		StringBuffer query = new StringBuffer(" select a.areaCode from MTArea a");
		StringBuffer whereClause = new StringBuffer(" where a.areaDesc =  '" + areaDesc + "'");

		query = query.append(whereClause.toString());
		Long emp = (long) 0;
		try {
			emp = (Long) entityManager.createQuery(query.toString()).getSingleResult();

		} catch (Exception e) {
			return 0;
		}
		return emp;
	}

	public List<MTEmployee> getEmployeeListYearArea(String area, String year) {
		StringBuffer query = null;
		StringBuffer whereClause = null;
		StringBuffer query1 = null;
		StringBuffer whereClause1 = null;

		StringBuffer sb = new StringBuffer();

		if (!area.equals("0")) {
			query = new StringBuffer("select distinct do from MTEmployee  do LEFT JOIN FETCH do.emp  emp1  ");
			whereClause = new StringBuffer(" WHERE  do.usertype='USER'");

			area = "'" + area + "'";
			whereClause.append(" AND emp1.employeeArea.areaCode = " + area + " AND emp1.year =" + year + "  ");

		} else {
			query = new StringBuffer("select distinct do from MTEmployee do  LEFT JOIN FETCH do.emp emp1 ");
			whereClause = new StringBuffer(" WHERE do.usertype='USER' AND emp1.year =" + year + " ");
		}
		query = query.append(whereClause.toString());

		List<MTEmployee> emp = entityManager.createQuery(query.toString()).getResultList();

		Iterator<MTEmployee> emp2 = emp.iterator();

		while (emp2.hasNext()) {
			MTEmployee mtEmployee = (MTEmployee) emp2.next();
			sb.append("'" + mtEmployee.getEmpId() + "'");
			if (emp2.hasNext()) {
				sb.append(",");
			}

		}
		if (!(sb.length() > 0)) {
			sb.append("''");
		}
		query1 = new StringBuffer("select distinct do from MTEmployee do  LEFT JOIN FETCH do.emp emp1 ");
		whereClause1 = new StringBuffer(" WHERE  do.usertype='USER' AND do.empId in (" + sb + ")  AND emp1.year ="
				+ year + " order by do.fName asc ");
		query1 = query1.append(whereClause1.toString());
		List<MTEmployee> emp1 = entityManager.createQuery(query1.toString()).getResultList();

		return emp1;
	}

	public List<Object> getEmployeeYear(String year, String empId, String choose) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();

		StringBuffer query = new StringBuffer(" select distinct do from MTEmployee do LEFT JOIN FETCH do.emp emp1");
		StringBuffer whereClause = new StringBuffer(" WHERE  do.usertype='USER'");
		whereClause.append(" AND emp1.year = " + year + " AND do.empId ='" + empId + "'");

		query = query.append(whereClause.toString());

		MTEmployee emp = (MTEmployee) entityManager.createQuery(query.toString()).getSingleResult();

		Iterator<AreaEmpMapping> emp2 = emp.getEmp().iterator();

		while (emp2.hasNext()) {
			AreaEmpMapping mtEmployee = (AreaEmpMapping) emp2.next();
			String year1 = Year.now().toString();
			
			/** Please make it dynamic year instead of Static 2018 **/
			if (year1.equals(mtEmployee.getYear())) {
				sb.append("'" + mtEmployee.getEmployeeArea().getAreaCode() + "'");
				if (emp2.hasNext()) {
					sb.append(",");
				}
			}

		}

		if (!(sb.length() > 0)) {
			sb.append("''");
		}

		List<Object> n = entityManager
				.createQuery(
						"select distinct do from MTArea do where do.areaCode in (" + sb + ") order by area_order asc")
				.getResultList();
		if (choose == "N") {
			return n;
		}
		Iterator<Object> n1 = n.iterator();
		while (n1.hasNext()) {
			MTArea object = (MTArea) n1.next();
			sb1.append("'" + object.getAreaCode() + "'");
			if (n1.hasNext()) {
				sb1.append(",");
			}
		}

		if (!(sb1.length() > 0)) {
			sb1.append("''");
		}

		List<Object> n3 = entityManager.createQuery(
				"select distinct do from MTQuestion do left join fetch do.questionArea qa left join fetch do.yearMap ym where do.isActive='Y' and qa.areaCode in ("
						+ sb1 + ") and ym.year=" + year + " order by do.questionId asc")
				.getResultList();

		return n3;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean sapEmployeeIntCreate(String empId) {
		SAPWebService sap = new SAPWebService();
		try {
			boolean isUpdate = false;
			HashMap<String, String> integratedSAP = sap.getEmpDetails(sap, empId + "");
			if (!integratedSAP.get("empId").equalsIgnoreCase("false")) {
				// format age to float
				float age = Float.parseFloat(integratedSAP.get("age"));

				Date date = null;
				java.util.Date dateString = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH)
						.parse(integratedSAP.get("dob"));
				String tempDate = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).format(dateString);
				/*
				 * DateFormat newDate = new SimpleDateFormat("yyyy-mm-dd"); date
				 * = newDate.parse(tempDate); newDate = new
				 * SimpleDateFormat("yyyy-mm-dd"); date=newDate.format(date);
				 */logger.info("Formated Date:" + tempDate);

				MTEmployee updateEmpDet = new MTEmployee();
				// getemp=employeedao.findOne(empId);

				// long pkEmp=employeedao.findOne(empId).getPkEmp();
				List<MTEmployee> pkEmp1 = get(empId);
				Iterator<MTEmployee> pkEmp2 = pkEmp1.iterator();
				if (pkEmp2.hasNext()) {
					MTEmployee emp4 = pkEmp2.next();
					MTPacode pac=new MTPacode();
					if (integratedSAP.get("sector") != null) {
						pac = getPaDetails(integratedSAP.get("sector"));
						updateEmpDet.setPa(integratedSAP.get("sector"));
						updateEmpDet.setBusiness(pac.getPabusiness().getBusinessId());
						updateEmpDet.setSector(pac.getPabusiness().getMtsector().getSectorId());
					}
					updateEmpDet.setAge(age);
					updateEmpDet.setDateOfBirth(tempDate);
					if (integratedSAP.get("gender") != null) {
						updateEmpDet.setGender(integratedSAP.get("gender"));
					}
					if (integratedSAP.get("country") != null) {
						updateEmpDet.setCountry(integratedSAP.get("country"));
					}
					if (integratedSAP.get("location") != null) {
						updateEmpDet.setCity(integratedSAP.get("location"));
					}
					updateEmpDet.setEmpId(empId);
					updateEmpDet.setModifiedBy("admin");
					updateEmpDet.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
					
					if(pac.getSsoFlag()!=null && "Y".equalsIgnoreCase(pac.getSsoFlag())){
					updateEmpDet.setSapFlag("Y");
					/*updateEmpDet.setPassword("sso");*/
					updateEmpDet.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
					updateEmpSAPDetails(updateEmpDet);
					}else{
						updateEmpDet.setSapFlag("N");
						updateEmpDet.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
						updateEmpSAPDetails(updateEmpDet);
					}
					// update()(updateEmpDet);
					isUpdate = true;
				} else {
					logger.info("Not found in DB ");
				}
			} else {
				logger.info("Not found in SAP ");
				isUpdate = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}

	// <--SAP integration after upload-->
	// --author Pooja
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean sapEmployeeInt() {
		int year = Calendar.getInstance().get(Calendar.YEAR);// get current year
		String curYear = "" + year;

		List<MTEmployee> empList = getEmployeeListYear(curYear);// get employees
																// for current
																// year
		boolean isUpdate = false;
		Iterator<MTEmployee> iterator = empList.iterator();
		MTEmployee empObj = null;
		while (iterator.hasNext()) {
			empObj = iterator.next();
			logger.info(empObj.getEmpId());
			String empId = empObj.getEmpId();

			SAPWebService sap = new SAPWebService();

			try {
				HashMap<String, String> integratedSAP = sap.getEmpDetails(sap, empId + ""); // get
																							// emp
																							// details
																							// from
																							// SAP
																							// webservice
				if (!integratedSAP.get("empId").equalsIgnoreCase("false")) {
					// format age to float
					float age=0;
					if(integratedSAP.get("age")!=null)
					age = Float.parseFloat(integratedSAP.get("age"));

					Date date = null;
					java.util.Date dateString = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH)
							.parse(integratedSAP.get("dob"));
					String tempDate = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).format(dateString);
					/*
					 * DateFormat newDate = new SimpleDateFormat("yyyy-mm-dd");
					 * date = newDate.parse(tempDate); newDate = new
					 * SimpleDateFormat("yyyy-mm-dd");
					 * date=newDate.format(date);
					 */logger.info("Formated Date:" + tempDate);

					MTEmployee updateEmpDet = new MTEmployee();
					MTEmployee getemp = new MTEmployee();
					// getemp=employeedao.findOne(empId);

					// long pkEmp=employeedao.findOne(empId).getPkEmp();
					List<MTEmployee> pkEmp1 = get(empId);
					Iterator<MTEmployee> pkEmp2 = pkEmp1.iterator();
					if (pkEmp2.hasNext()) {
						
						MTEmployee emp4 = pkEmp2.next();
						if(!emp4.getEmailId().substring(emp4.getEmailId().indexOf('@') + 1).equalsIgnoreCase("bcone.com") && !emp4.getEmailId().substring(emp4.getEmailId().indexOf('@') + 1).equalsIgnoreCase("techmahindra.com"))
						{
							MTPacode pac=new MTPacode();	
						if (integratedSAP.get("sector") != null) {
							pac = getPaDetails(integratedSAP.get("sector"));
							updateEmpDet.setPa(integratedSAP.get("sector"));
							if(pac!=null && pac.getPabusiness()!=null)
							updateEmpDet.setBusiness(pac.getPabusiness().getBusinessId());
							if(pac!=null && pac.getPabusiness()!=null && pac.getPabusiness().getMtsector()!=null)
							updateEmpDet.setSector(pac.getPabusiness().getMtsector().getSectorId());
						}
						if(integratedSAP.get("emailId").substring(integratedSAP.get("emailId").indexOf('@') + 1).equalsIgnoreCase("mahindra.com"))
						updateEmpDet.setEmailId(integratedSAP.get("emailId"));
						updateEmpDet.setAge(age);
						updateEmpDet.setDateOfBirth(tempDate);
						if (integratedSAP.get("gender") != null) {
							updateEmpDet.setGender(integratedSAP.get("gender"));
						}
						if (integratedSAP.get("country") != null) {
							updateEmpDet.setCountry(integratedSAP.get("country"));
						}
						if (integratedSAP.get("location") != null) {
							updateEmpDet.setCity(integratedSAP.get("location"));
						}
						//Added By rakesh kumar Start:
						updateEmpDet.setEmpId(empId);
						updateEmpDet.setModifiedBy("admin");
						updateEmpDet.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
						System.out.println("integratedSAP.get(emailId)==>"+integratedSAP.get("emailId"));
						System.out.println("pac.getSsoFlag()===>"+pac.getSsoFlag());
						if(pac.getSsoFlag()!=null && "Y".equalsIgnoreCase(pac.getSsoFlag())){
							updateEmpDet.setSapFlag("Y");
							/*updateEmpDet.setPassword("sso");*/
							updateEmpDet.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
							updateEmpSAPDetails(updateEmpDet);
						}else{
							updateEmpDet.setSapFlag("N");
							updateEmpDet.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
							updateEmpSAPDetails(updateEmpDet);
						}
						// update()(updateEmpDet);
						}
						isUpdate = true;
					} else {
						logger.info("Not found in DB ");
					}
				} else {
					logger.info("Not found in SAP ");
					isUpdate = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return isUpdate;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean firstTimeLogin(MTEmployee mtLogin) {
		boolean isUpdate = false;
		String empId = mtLogin.getEmpId();

			SAPWebService sap = new SAPWebService();

			try {
				HashMap<String, String> integratedSAP = sap.getEmpDetails(sap, empId + ""); // get
																							// emp
																							// details
																							// from
																							// SAP
																							// webservice
				if (!integratedSAP.get("empId").equalsIgnoreCase("false")) {
					// format age to float
					float age=0;
					if(integratedSAP.get("age")!=null)
					age = Float.parseFloat(integratedSAP.get("age"));

					Date date = null;
					java.util.Date dateString = new SimpleDateFormat("yyyymmdd", Locale.ENGLISH)
							.parse(integratedSAP.get("dob"));
					String tempDate = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).format(dateString);
					/*
					 * DateFormat newDate = new SimpleDateFormat("yyyy-mm-dd");
					 * date = newDate.parse(tempDate); newDate = new
					 * SimpleDateFormat("yyyy-mm-dd");
					 * date=newDate.format(date);
					 */logger.info("Formated Date:" + tempDate);

					MTEmployee updateEmpDet = new MTEmployee();
					MTEmployee getemp = new MTEmployee();
					// getemp=employeedao.findOne(empId);

					// long pkEmp=employeedao.findOne(empId).getPkEmp();
					List<MTEmployee> pkEmp1 = get(empId);
					Iterator<MTEmployee> pkEmp2 = pkEmp1.iterator();
					if (pkEmp2.hasNext()) {
						
						MTEmployee emp4 = pkEmp2.next();
						if(!emp4.getEmailId().substring(emp4.getEmailId().indexOf('@') + 1).equalsIgnoreCase("bcone.com") && !emp4.getEmailId().substring(emp4.getEmailId().indexOf('@') + 1).equalsIgnoreCase("techmahindra.com"))
						{
							MTPacode pac=new MTPacode();	
						if (integratedSAP.get("sector") != null) {
							pac = getPaDetails(integratedSAP.get("sector"));
							updateEmpDet.setPa(integratedSAP.get("sector"));
							if(pac!=null && pac.getPabusiness()!=null)
							updateEmpDet.setBusiness(pac.getPabusiness().getBusinessId());
							if(pac!=null && pac.getPabusiness()!=null && pac.getPabusiness().getMtsector()!=null)
							updateEmpDet.setSector(pac.getPabusiness().getMtsector().getSectorId());
						}
						if(integratedSAP.get("emailId").substring(integratedSAP.get("emailId").indexOf('@') + 1).equalsIgnoreCase("mahindra.com"))
						updateEmpDet.setEmailId(integratedSAP.get("emailId"));
						updateEmpDet.setAge(age);
						updateEmpDet.setDateOfBirth(tempDate);
						if (integratedSAP.get("gender") != null) {
							updateEmpDet.setGender(integratedSAP.get("gender"));
						}
						if (integratedSAP.get("country") != null) {
							updateEmpDet.setCountry(integratedSAP.get("country"));
						}
						if (integratedSAP.get("location") != null) {
							updateEmpDet.setCity(integratedSAP.get("location"));
						}
						//Added By rakesh kumar Start:
						updateEmpDet.setEmpId(empId);
						updateEmpDet.setModifiedBy("admin");
						updateEmpDet.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
						System.out.println("integratedSAP.get(emailId)==>"+integratedSAP.get("emailId"));
						System.out.println("pac.getSsoFlag()===>"+pac.getSsoFlag());
						if(pac.getSsoFlag()!=null && "Y".equalsIgnoreCase(pac.getSsoFlag())){
							updateEmpDet.setSapFlag("Y");
							/*updateEmpDet.setPassword("sso");*/
							updateEmpDet.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
							updateEmpSAPDetails(updateEmpDet);
						}else{
							updateEmpDet.setSapFlag("N");
							updateEmpDet.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
							updateEmpSAPDetails(updateEmpDet);
						}
						// update()(updateEmpDet);
						}
						isUpdate = true;
					} else {
						logger.info("Not found in DB ");
					}
				} else {
					logger.info("Not found in SAP ");
					isUpdate = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isUpdate;
		}
	

	/* * @SuppressWarnings("unchecked")
	 * 
	 * @Transactional(propagation = Propagation.REQUIRED) public
	 * List<MTEmployee> checkLogin(MTEmployee user) { // TODO Auto-generated
	 * method stub List<MTEmployee> users = new ArrayList<MTEmployee>(); try {
	 * 
	 * users = employeedao.findAll(" empId='" + user.getEmpId() +
	 * "' and ((year='" + user.getYear() +
	 * "' and usertype='USER' ) or  usertype='admin')");
	 * 
	 * logger.info("users" + users);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); logger.info(
	 * "Exception in Login Query"); return null; }
	 * 
	 * return users; }*/
	 

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public MTEmployee checkLoginExternal(String username, String password ) {
		// TODO Auto-generated method stub
		MTEmployee users = new MTEmployee();
		String token = null;
		
	 
		try {
			if("admin".equalsIgnoreCase(username)){
				users = employeedao.findOne("emailId='" +username+"'");
//			users = employeedao.findOne("emailId='" + username + "'and  password='" + password + "'");	
			}
			
			else
			 {
			   if(username.contains("@mahindra.com"))
			    {	
			     token=username.substring(0, username.indexOf("@mahindra.com"));
			    } 
			     else
			    {
				  token=username;
			    }
			 
		     //	users = employeedao.findOne("emailId='" + username + "' and year=year(now()) and  password='" + password + "'");
			    //users = employeedao.findOne("emailId='" + username + "' or empId ='"+token+"' and year=year(now()) and  password='" + password + "'"); // gaurav change login to go through token
			   users = employeedao.findOne("emailId='" + username + "'  and year=year(now()) "); // gaurav change login to go through token
//			   users = employeedao.findOne("emailId='" + username + "'  and year=year(now()) and  password='" + password + "'"); // gaurav change login to go through token
             }
        	//System.out.println("Logged in user==>" + users.toString());
			logger.info("users" + users);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in Login Query");
			return null;
		}
		return users;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateEmpSAPDetails(MTEmployee updatedEmpDetails) {

		entityManager.createQuery("update   MTEmployee set age = " + updatedEmpDetails.getAge() + ",dateOfBirth ='"
				+ updatedEmpDetails.getDateOfBirth() + "' , gender ='" + updatedEmpDetails.getGender() + "' ,"
				+ " sector='" + updatedEmpDetails.getSector() + "' , country ='" + updatedEmpDetails.getCountry()
				+ "' ,city='" + updatedEmpDetails.getCity() + "' , " + "  modifiedBy = '"
				+ updatedEmpDetails.getModifiedBy() + "' ,modifiedDateTime = '"
				+ updatedEmpDetails.getModifiedDateTime() + "' ,pa = '" + updatedEmpDetails.getPa() + "' ,business = '"
				+ updatedEmpDetails.getBusiness() + "' ,sector = '" + updatedEmpDetails.getSector()
				+ "' ,businessOthers = '" + updatedEmpDetails.getBusinessOthers() + "' ,sectorOthers = '"
				+ updatedEmpDetails.getSectorOthers() + "' ,sapFlag = '" + updatedEmpDetails.getSapFlag()+ "',password = '"+updatedEmpDetails.getPassword()
				+ "' where empId = '" + updatedEmpDetails.getEmpId() + "' ").executeUpdate();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEmpSAPDetailsUser(MTEmployee updatedEmpDetails) {

		entityManager.createQuery("update   MTEmployee set age = " + updatedEmpDetails.getAge() + ",dateOfBirth ='"
				+ updatedEmpDetails.getDateOfBirth() + "' , gender ='" + updatedEmpDetails.getGender() + "' ,"
				+ " sector='" + updatedEmpDetails.getSector() + "' , country ='" + updatedEmpDetails.getCountry()
				+ "' ,city='" + updatedEmpDetails.getCity() + "' , " + "  modifiedBy = '"
				+ updatedEmpDetails.getModifiedBy() + "' ,modifiedDateTime = '"
				+ updatedEmpDetails.getModifiedDateTime() + "', lname='" + updatedEmpDetails.getlName() + "' ,pa = '"
				+ updatedEmpDetails.getPa() + "' ,business = '" + updatedEmpDetails.getBusiness() + "' ,sector = '"
				+ updatedEmpDetails.getSector() + "' ,businessOthers = '" + updatedEmpDetails.getBusinessOthers()
				+ "' ,sectorOthers = '" + updatedEmpDetails.getSectorOthers() + "' where empId = '"
				+ updatedEmpDetails.getEmpId() + "' ").executeUpdate();

	}

	public MTPacode getPaDetails(String patext) {
		MTPacode pacode = null;
		try {
			pacode = (MTPacode) entityManager.createQuery("from MTPacode where paText='" + patext + "'").getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			//MTBusiness mtBusiness = new MTBusiness();
			//MTSector mtsector = new MTSector();
			//mtBusiness.setMtsector(mtsector);
			pacode = new MTPacode();
			//pacode.setPabusiness(mtBusiness);
		}

		return pacode;
	}

	public static float getAge(String dob) {

		float age = 0;
		try {
			Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dob);

			Calendar today = Calendar.getInstance();
			Calendar birthDate = Calendar.getInstance();

			birthDate.setTime(dateOfBirth);
			if (birthDate.after(today)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}

			age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

			// If birth date is greater than todays date (after 2 days
			// adjustment of leap year) then decrement age one year
			if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
					|| (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
				age--;

				// If birth date and todays date are of same month and birth day
				// of month is greater than todays day of month then decrement
				// age
			} else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
				age--;
			}
			return age;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return age;
	}

	@Override
	public MTEmployee getuserdetails(String username , String token) {
		if(username.contains("@mahindra.com"))
	    {	
	     token=username.substring(0, username.indexOf("@mahindra.com"));
	    } 
	     else
	    {
		  token=username;
	    }
		
		//return employeedao.findOne("emailId='" + username + "' or empId='" +token+"'"); // login in the portal user
		return employeedao.findOne("emailId='" +username+"'"); // login in the portal user change query gaurav

	}

	@Override
	public List<MTArea> getallarea() {
		return mtareadao.findAll();
	}

	

	@Override
	@Transactional
	public Boolean updateemp(MTEmployee emp) {
		
		emp.setCreatedBy("ADMIN");
		emp.setModifiedBy("ADMIN");
		emp.setIsActive("Y");
		if(emp.getEmailId()!=null && emp.getEmailId().contains("bcone.com")){
			emp.setSapFlag("Y");
		}else{
			if (!("Y".equalsIgnoreCase(emp.getSapFlag()))) {
				emp.setSapFlag("N");
			}	
		}
		emp.setUsertype("USER");
		if (passwordGenSSOdomainCheck.ssodomain(emp.getEmailId())) {
			/*emp.setPassword("sso");*/
			emp.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
		}else {
		emp.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
		}
		
//		emp.setPassword("mahindra");
	/*	
		Integer a =0;
		for (AreaEmpMapping areaEmpMapping : emp.getEmp()) {

			if (areaEmpMapping.getAreaCode() != null && areaEmpMapping.getAreaCode() != "") {

				areaEmpMapping.setCreatedBy("admin");
				areaEmpMapping.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
				areaEmpMapping.setIsActive("Y");
				areaEmpMapping.setModifiedBy("admin");
				
				emp.getEmp().set(a, areaEmpMapping);
			}
			a++;
		}*/
		MTEmployee newemp = employeedao.findOne(emp.getPkEmp());
		emp.setMtResponse(newemp.getMtResponse());
		
		try {
			employeedao.update(emp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public Boolean findareamapping(String area_code,String area_emp_id ,String year) {
		
		AreaEmpMapping areaempmap = areaEmpMappingDao.findOne("area_code='"+area_code+"' and area_emp_id='"+area_emp_id+"' and year='"+year+"'");
		
		if(areaempmap==null){
			return false;
		}else{
			return true;
		}
		
		
	}

	@Override
	@Transactional
	public Boolean insertareamapping(String area_code, String areaDesc, String area_emp_id, String year) {
		
		MTArea area = new MTArea();
		MTEmployee emp = new MTEmployee();
		AreaEmpMapping mtareaempmap= new AreaEmpMapping();
		mtareaempmap.setAreaCode(areaDesc);
		
		
		area.setAreaCode(Long.parseLong(area_code));
		mtareaempmap.setEmployeeArea(area);
		mtareaempmap.setYear(year);
		mtareaempmap.setCreatedBy("ADMIN");
		mtareaempmap.setIsActive("Y");
		mtareaempmap.setModifiedBy("ADMIN");
		mtareaempmap.setModifiedDateTime(new Timestamp(System.currentTimeMillis()));
		
		emp.setPkEmp(Long.parseLong(area_emp_id));
		mtareaempmap.setMtemployee(emp);
		
	 
		
		try {
			areaEmpMappingDao.create(mtareaempmap);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public List<AreaDTO> getallcurryearactivearea() {
		
		Query query = entityManager.createNativeQuery("SELECT a.area_code,a.area_desc FROM "
				+ "annual_survey.area_year_mapping "
				+ "amp inner join mt_area a on amp.area_code=a.area_code where year=year(now()) "
				+ "and  amp.is_active='Y'");
		
		@SuppressWarnings("unchecked")
		List<Object[]> object = query.getResultList();
		List<AreaDTO> arealist = new ArrayList<>();
		for(Object[] ob : object ){
			AreaDTO area = new AreaDTO();
			area.setAreaCode(Long.parseLong(ob[0].toString()));
			area.setAreaDesc(ob[1].toString());
			arealist.add(area);
		}

		return arealist;
	}

}
