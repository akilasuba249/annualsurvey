/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dto.CommentsDTO;
import com.mahindra.annualsurvey.dto.OpenEndedDTO;
import com.mahindra.annualsurvey.dto.YesNoDTO;

/**
 * @author Yash Mahale
 *
 */
public interface ReportService {

	List<YesNoDTO> getyesNoReport(String year);

	List<CommentsDTO> getCommentReport(String year);

	List<OpenEndedDTO> getOpenEndedReport(String year);
	
	public void downloadReports(String area_code,String year);

}
