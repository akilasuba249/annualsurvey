package com.mahindra.annualsurvey.controllers;

import java.io.ByteArrayInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mahindra.annualsurvey.constants.Commonpaths;
import com.mahindra.annualsurvey.dao.MTEmployeeDAO;
import com.mahindra.annualsurvey.dto.AreaDTO;
import com.mahindra.annualsurvey.dto.EmpExcelDto;
import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.dto.QuestionDTO;
import com.mahindra.annualsurvey.model.MTEmployee;
import com.mahindra.annualsurvey.model.MTSubQuestion;
import com.mahindra.annualsurvey.services.MTAreaService;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.MTQuestionService;
import com.mahindra.annualsurvey.services.MTQuestionTypeService;

import com.mahindra.annualsurvey.utils.FileBean;
import com.mahindra.annualsurvey.utils.FileDownloadUtil;
import com.mahindra.annualsurvey.utils.PasswordGenSSOdomainCheck;
import com.mahindra.annualsurvey.utils.AES;
import com.mahindra.annualsurvey.utils.Validations;

@Controller
public class FileUploadController {

	static final Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	MTEmployeeService empservice;

	@Autowired
	MTQuestionTypeService questionservice;

	@Autowired
	MTQuestionService questservice;

	@Autowired
	MTAreaService mtareaservice;

	@Autowired
	private MTEmployeeDAO employeedao;

	@Autowired
	private PasswordGenSSOdomainCheck passwordGenSSOdomainCheck;

	@Autowired
	private FileDownloadUtil fileDownloadUtil;
	
	@Autowired
	private AES encryption;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse resp,
			@ModelAttribute("FileBean") FileBean uploadItem, Model m,
			BindingResult result, @ModelAttribute("EmployeeDTO") EmployeeDTO emp)
			throws IOException {
		boolean isSAPDataIntegrated = false;
		if ("Employee".equals(uploadItem.getName())) {

			Map<String, List<EmpExcelDto>> exceldata = import1(uploadItem);
			Boolean haserror = addEmpandMapping(exceldata, uploadItem.getYear());

			if (haserror) {
				m.addAttribute("result", "error");
			} else {
				m.addAttribute("result", "okay");
			}

			/*
			 * resp.setContentType("application/vnd.ms-excel");
			 * 
			 * resp.setHeader("Content-Disposition",
			 * "attachment; filename=\"ErrorRecords.xlsx");
			 * 
			 * workbook.write(resp.getOutputStream());
			 */

			// isSAPDataIntegrated = empservice.sapEmployeeInt();

		} else {
			import2(uploadItem);
			m.addAttribute("result", "okay");
		}

		return "redirect:admin";

	}

	@RequestMapping(value = "/downloadErrorExcel", method = RequestMethod.GET)
	@ResponseBody
	public boolean downloadErrorFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String filepath = Commonpaths.DOWNLOAD_ERROR_EXCEL + "ErrorRecord"
				+ ".xlsx";
		fileDownloadUtil.downloadfile(request, response, filepath);
		return true;

	}

	/*
	 * public boolean import1(FileBean fileBean) throws IOException {
	 * 
	 * ByteArrayInputStream bis = new
	 * ByteArrayInputStream(fileBean.getFileData().getBytes()); Workbook
	 * workbook = null; try { if
	 * (fileBean.getFileData().getOriginalFilename().endsWith("xls")) { workbook
	 * = new HSSFWorkbook(bis); } else if
	 * (fileBean.getFileData().getOriginalFilename().endsWith("xlsx")) {
	 * workbook = new XSSFWorkbook(bis); } else { throw new
	 * IllegalArgumentException(
	 * "Received file does not have a standard excel extension."); }
	 * 
	 * Sheet sheet = workbook.getSheetAt(0); Row row; Cell cell;
	 * 
	 * int rows; // No of rows rows = sheet.getPhysicalNumberOfRows(); int cols
	 * = 0; // No of columns int tmp = 0;
	 * 
	 * for (int i = 0; i < 0 || i < rows; i++) { row = sheet.getRow(i); if (row
	 * != null) { tmp = sheet.getRow(i).getPhysicalNumberOfCells(); if (tmp >
	 * cols) cols = tmp; } }
	 * 
	 * for (int r = 1; r <= rows; r++) { row = sheet.getRow(r); if (row != null)
	 * { EmployeeDTO emp = new EmployeeDTO(); List<AreaEmpDTO> areaemp = new
	 * ArrayList<AreaEmpDTO>(); int j = 0; for (int c = 1; c <= cols; c++) {
	 * cell = row.getCell((short) c);
	 * 
	 * switch (c) { case 1: List<MTEmployee> employee = null; if
	 * (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) { BigDecimal bd = new
	 * BigDecimal((Double) cell.getNumericCellValue()); long lonVal =
	 * bd.longValue();
	 * 
	 * employee = empservice.get(Long.toString(lonVal));
	 * 
	 * } else { employee = empservice.get(cell.getStringCellValue()); }
	 * Iterator<MTEmployee> itr = employee.iterator();
	 * 
	 * while (itr.hasNext()) { j++; MTEmployee mtEmployee = (MTEmployee)
	 * itr.next(); List<AreaEmpMapping> a = mtEmployee.getEmp(); Iterator i =
	 * a.iterator(); while (i.hasNext()) { AreaEmpMapping object =
	 * (AreaEmpMapping) i.next(); AreaEmpDTO areempdto = new AreaEmpDTO();
	 * areempdto.setAreaCode(object.getAreaCode());
	 * areempdto.setAreaCode1(object.getEmployeeArea().getAreaCode());
	 * areempdto.setCreatedBy(object.getCreatedBy());
	 * areempdto.setCreatedDateTime(object.getCreatedDateTime());
	 * areempdto.setIsActive(object.getIsActive());
	 * areempdto.setModifiedBy(object.getModifiedBy());
	 * areempdto.setModifiedDateTime(object.getModifiedDateTime());
	 * areempdto.setYear(object.getYear()); areaemp.add(areempdto);
	 * 
	 * empservice.delete(object.getPkAreaEmp()); }
	 * emp.setPkEmp(mtEmployee.getPkEmp()); emp.setAge(mtEmployee.getAge());
	 * emp.setBusiness(mtEmployee.getBusiness());
	 * emp.setCity(mtEmployee.getCity());
	 * emp.setCountry(mtEmployee.getCountry());
	 * emp.setDateOfBirth(mtEmployee.getDateOfBirth());
	 * emp.setSapFlag(mtEmployee.getSapFlag());
	 * emp.setSector(mtEmployee.getSector());
	 * emp.setSectorOthers(mtEmployee.getSectorOthers());
	 * 
	 * } if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) { BigDecimal bd = new
	 * BigDecimal((Double) cell.getNumericCellValue()); long lonVal =
	 * bd.longValue();
	 * 
	 * emp.setEmpId(Long.toString(lonVal)); } else {
	 * emp.setEmpId(cell.getStringCellValue()); } break; case 2:
	 * emp.setfName(cell.getStringCellValue()); break; case 3:
	 * emp.setlName(cell.getStringCellValue()); break; case 4:
	 * emp.setEmailId(cell.getStringCellValue()); break; case 5: int k = 0;
	 * Iterator<AreaEmpDTO> areempdto1 = areaemp.iterator(); while
	 * (areempdto1.hasNext()) { AreaEmpDTO areaEmpDTO = (AreaEmpDTO)
	 * areempdto1.next(); if
	 * (areaEmpDTO.getAreaCode().equalsIgnoreCase(cell.getStringCellValue()) &&
	 * areaEmpDTO.getYear().equalsIgnoreCase(fileBean.getYear())) { k++; }
	 * 
	 * } AreaEmpDTO areempdto2 = new AreaEmpDTO(); if (k == 0) {
	 * areempdto2.setAreaCode(cell.getStringCellValue()); long h = 0; h =
	 * empservice.getArea(areempdto2.getAreaCode()); areempdto2.setAreaCode1(h);
	 * areempdto2.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
	 * areempdto2.setIsActive("Y"); areempdto2.setYear(fileBean.getYear()); if
	 * (h > 0) { areaemp.add(areempdto2); } } emp.setArea(areaemp); break;
	 * default: break; } }
	 * 
	 * if (j == 0) { emp.setYear(fileBean.getYear()); empservice.create(emp); }
	 * else { emp.setYear(fileBean.getYear());
	 * 
	 * empservice.update(emp); } } }
	 * 
	 * } catch (Exception e) { return false; } finally { // workbook.close(); //
	 * bis.close(); } return true; }
	 */

	@SuppressWarnings("resource")
	public Map<String, List<EmpExcelDto>> import1(FileBean fileBean)
			throws IOException {

		Map<String, List<EmpExcelDto>> map = new HashMap<String, List<EmpExcelDto>>();
		List<EmpExcelDto> empexcellist = new ArrayList<>();
		List<EmpExcelDto> errorempexcellist = new ArrayList<>();

		ByteArrayInputStream bis = new ByteArrayInputStream(fileBean
				.getFileData().getBytes());
		Workbook workbook = null;
		try {

			// start:- this will he
			if (fileBean.getFileData().getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(bis);
			} else if (fileBean.getFileData().getOriginalFilename()
					.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(bis);
			} else {
				throw new IllegalArgumentException(
						"Received file does not have a standard excel extension.");
			}

			Sheet sheet = workbook.getSheetAt(0);
			Row row;

			for (int r = 1; r <= sheet.getLastRowNum(); r++) {

				EmpExcelDto empexceldto = new EmpExcelDto();

				row = sheet.getRow(r);
				Boolean hasError = false;
				StringBuilder sb = new StringBuilder();

				if (row != null) {

					if (row.getCell(1) == null
							|| row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {

						hasError = true;
						sb.append("* Employee ID Should not Blank");
						empexceldto.setRemark(sb.toString());

					} else if (row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						empexceldto.setEmpid(String.valueOf(new BigDecimal(row
								.getCell(1).getNumericCellValue())
								.toPlainString()));

					} else {
						empexceldto.setEmpid(row.getCell(1)
								.getStringCellValue());
					}

					if (row.getCell(2) == null
							|| row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) {

						hasError = true;
						sb.append("\t*First Name Should not Blank");
						empexceldto.setRemark(sb.toString());

					} else {
						empexceldto.setFname(row.getCell(2)
								.getStringCellValue());
					}

					if (row.getCell(3) == null
							|| row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {

						hasError = true;
						sb.append("\t* Last Name Should not Blank");
						empexceldto.setRemark(sb.toString());

					} else {
						empexceldto.setLname(row.getCell(3)
								.getStringCellValue());
					}

					if (row.getCell(4) == null
							|| row.getCell(4).getCellType() == Cell.CELL_TYPE_BLANK) {

						hasError = true;
						sb.append("\t* Email Should not Blank");
						empexceldto.setRemark(sb.toString());

					} else if (!(Validations.isEmailValidate(row.getCell(4)
							.getStringCellValue()))) {
						hasError = true;
						sb.append("\t* Email is not proper");
						empexceldto.setRemark(sb.toString());
						empexceldto.setEmailid(row.getCell(4)
								.getStringCellValue());
					} else {
						empexceldto.setEmailid(row.getCell(4)
								.getStringCellValue());
					}

					if (row.getCell(5) == null
							|| row.getCell(5).getCellType() == Cell.CELL_TYPE_BLANK) {
						hasError = true;
						sb.append("\t* Area code Should not Blank");
						empexceldto.setRemark(sb.toString());

					} else if (getareacode(row.getCell(5).getStringCellValue()) == null) {
						hasError = true;
						sb.append("\t* Area code does not exist or not active for current year");
						empexceldto.setRemark(sb.toString());
						empexceldto.setAreacode(row.getCell(5)
								.getStringCellValue());

					} else {
						empexceldto.setAreacode(row.getCell(5)
								.getStringCellValue());
					}

					if (hasError) {
						errorempexcellist.add(empexceldto);
					} else {
						empexcellist.add(empexceldto);
					}

					map.put("errorlist", errorempexcellist);
					map.put("list", empexcellist);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	public Boolean addEmpandMapping(
			Map<String, List<EmpExcelDto>> empexceldtolist, String year)
			throws IOException {

		for (EmpExcelDto empexceldto : empexceldtolist.get("list")) {// new
																		// employee
																		// red
			// from excel

			List<MTEmployee> employeedetailslist = empservice.get(empexceldto
					.getEmpid());// getting
									// the
									// emp
									// details
									// from
									// emp
									// id
									// for
									// each
									// emp

			if (!employeedetailslist.isEmpty()) {

				for (MTEmployee mtempdetils : employeedetailslist) {// iterating
																	// each
																	// detail
																	// and
																	// adding to
																	// dto
					MTEmployee mtemp = new MTEmployee();

					mtemp.setEmpId(empexceldto.getEmpid());
					mtemp.setfName(empexceldto.getFname());
					mtemp.setlName(empexceldto.getLname());

					mtemp.setIsActive("Y");
					// start emp dto binding
					mtemp.setEmailId(empexceldto.getEmailid());
					mtemp.setPkEmp(mtempdetils.getPkEmp());
					mtemp.setAge(mtempdetils.getAge());
					mtemp.setBusiness(mtempdetils.getBusiness());
					mtemp.setCity(mtempdetils.getCity());
					mtemp.setCountry(mtempdetils.getCountry());
					mtemp.setDateOfBirth(mtempdetils.getDateOfBirth());
					mtemp.setSapFlag(mtempdetils.getSapFlag());
					mtemp.setSector(mtempdetils.getSector());
					mtemp.setSectorOthers(mtempdetils.getSectorOthers());

					// String
					// area=String.valueOf(getareacode(empexceldto.getAreacode()));

					Boolean result = empservice.findareamapping(String
							.valueOf(getareacode(empexceldto.getAreacode())),
							String.valueOf(mtempdetils.getPkEmp()), year);

					if (!result) {
						empservice.insertareamapping(
								String.valueOf(getareacode(empexceldto
										.getAreacode())), empexceldto
										.getAreacode(), String
										.valueOf(mtempdetils.getPkEmp()), year);
					}
					mtemp.setYear(year);

					mtemp.setEmp(mtempdetils.getEmp());
					empservice.updateemp(mtemp);
				}

			} else {

				MTEmployee mtNewemp = new MTEmployee();

				mtNewemp.setEmpId(empexceldto.getEmpid());
				mtNewemp.setfName(empexceldto.getFname());
				mtNewemp.setlName(empexceldto.getLname());
				mtNewemp.setEmailId(empexceldto.getEmailid());
				mtNewemp.setCreatedBy("admin");
				mtNewemp.setIsActive("Y");
				
				if(empexceldto.getEmailid()!=null && empexceldto.getEmailid().contains("bcone.com")){
					mtNewemp.setSapFlag("Y");
					/*mtNewemp.setPassword("sso");*/
				}else{
					mtNewemp.setSapFlag("N");
					/*mtNewemp.setPassword(passwordGenSSOdomainCheck.getRandomString(5));*/
				}
				mtNewemp.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
				
				
				mtNewemp.setCreatedDateTime(new Timestamp(System
						.currentTimeMillis()));
				mtNewemp.setUsertype("USER");

				//mtNewemp.setPassword(passwordGenSSOdomainCheck.getRandomString(5));
				/*
				 * for (AreaEmpMapping areaempmapping : mtNewemp.getEmp()) {
				 * 
				 * // AreaEmpMapping newAreaEmpMapping = new AreaEmpMapping();
				 * areaempmapping.setAreaCode(empexceldto.getAreacode());
				 * areaempmapping.getEmployeeArea().setAreaCode(getareacode(
				 * empexceldto.getAreacode()));
				 * areaempmapping.setCreatedDateTime(new
				 * Timestamp(System.currentTimeMillis()));
				 * areaempmapping.setIsActive("Y");
				 * areaempmapping.setYear(year);
				 * mtNewemp.getEmp().add(areaempmapping);
				 * 
				 * } mtNewemp.setEmp(mtNewemp.getEmp());
				 */
				mtNewemp.setYear(year);
				MTEmployee newmtempdetils = employeedao
						.addnewemployee(mtNewemp);
				empservice.insertareamapping(
						String.valueOf(getareacode(empexceldto.getAreacode())),
						empexceldto.getAreacode(),
						String.valueOf(newmtempdetils.getPkEmp()), year);

			}

		}

		List<EmpExcelDto> errorlist = empexceldtolist.get("errorlist");
		XSSFWorkbook workbook = new XSSFWorkbook();

		if (!errorlist.isEmpty()) {
			@SuppressWarnings("resource")
			// create a wordsheet
			XSSFSheet sheet = workbook.createSheet("Error Records");

			XSSFRow header = sheet.createRow(0);
			header.createCell(1).setCellValue("Employee ID");
			header.createCell(2).setCellValue("First Name");
			header.createCell(3).setCellValue("Last Name");
			header.createCell(4).setCellValue("Email Id");
			header.createCell(5).setCellValue("Area Code");
			header.createCell(6).setCellValue("Error Reason");

			int rowNum = 1;
			for (EmpExcelDto empexceldto : errorlist) {
				// create the row data
				XSSFRow row = sheet.createRow(rowNum++);
				row.createCell(1).setCellValue(empexceldto.getEmpid());
				row.createCell(2).setCellValue(empexceldto.getFname());
				row.createCell(3).setCellValue(empexceldto.getLname());
				row.createCell(4).setCellValue(empexceldto.getEmailid());
				row.createCell(5).setCellValue(empexceldto.getAreacode());
				row.createCell(6).setCellValue(empexceldto.getRemark());
			}

			// String user =
			// SecurityContextHolder.getContext().getAuthentication().getName();
			String filepath = Commonpaths.DOWNLOAD_ERROR_EXCEL + "ErrorRecord"
					+ ".xlsx";
			FileOutputStream output_file = new FileOutputStream(filepath);
			workbook.write(output_file);
			output_file.flush();
			output_file.close();

			return true;
		}

		return false;

	}

	public Long getareacode(String areaname) {

		List<AreaDTO> areayermaplist = empservice.getallcurryearactivearea();

		if (!areayermaplist.isEmpty()) {
			Long areacode = null;
			Map<Long, String> areayearMap = new HashMap<>();

			for (AreaDTO areaDTO : areayermaplist) {
				areayearMap.put(areaDTO.getAreaCode(), areaDTO.getAreaDesc());
			}

			for (Entry<Long, String> entry : areayearMap.entrySet()) {
				if (entry.getValue().equals(areaname.trim())) {
					areacode = entry.getKey();
					break;
				}
			}
			return areacode;
		} else {
			return null;
		}

	}

	public void import2(FileBean fileBean) throws IOException {

		ByteArrayInputStream bis = new ByteArrayInputStream(fileBean
				.getFileData().getBytes());
		Workbook workbook = null;
		try {
			if (fileBean.getFileData().getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(bis);
			} else if (fileBean.getFileData().getOriginalFilename()
					.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(bis);
			} else {
				throw new IllegalArgumentException(
						"Received file does not have a standard excel extension.");
			}

			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			Cell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();
			int cols = 0; // No of columns
			int tmp = 0;

			for (int i = 0; i < 0 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			for (int r = 1; r <= rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					cols = sheet.getRow(r).getPhysicalNumberOfCells();
					QuestionDTO n = new QuestionDTO();
					List<MTSubQuestion> n1 = new ArrayList<MTSubQuestion>();
					int l = 0;
					for (int c = 0; c <= cols; c++) {
						MTSubQuestion n2 = new MTSubQuestion();
						cell = row.getCell((short) c);

						switch (c) {
						case 1:
							int b = questionservice.getQuestionTypeID(cell
									.getStringCellValue());
							if (b == 0) {
								l++;
							}
							n.setQuestionType(b);
							if (n.getQuestionType() != 4) {
								n2.setSubQuestDesc("Question Type"
										+ questionservice
												.getQuestionTypeID(cell
														.getStringCellValue()));
								n1.add(n2);
								n2 = null;
							}
							break;
						case 2:
							n.setQuestionDesc(cell.getStringCellValue());
							break;
						case 3:
							long a = 0;
							a = mtareaservice.select(cell.getStringCellValue());
							n.setAreaCode(a);

							if (a == 0) {
								l++;
							}
							break;

						case 4:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 5:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 6:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 7:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 8:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 9:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 10:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 11:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 12:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 13:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 14:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 15:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 16:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 17:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						case 18:
							n2.setSubQuestDesc(cell.getStringCellValue());
							n1.add(n2);
							n2 = null;
							break;

						default:
							break;
						}
						n.setYear(Integer.parseInt(fileBean.getYear()));
					}

					n.setSubquest(n1);
					if (l < 1) {
						questservice.create(n);
					}
					n1 = null;
					n = null;
				}

			}

		} catch (Exception e) {
			logger.info("IN Catch");
			e.printStackTrace();
		} finally {
			// workbook.close();
			bis.close();
		}
	}

}
