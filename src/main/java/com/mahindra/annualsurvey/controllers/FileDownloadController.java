package com.mahindra.annualsurvey.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahindra.annualsurvey.dto.EmployeeDTO;
import com.mahindra.annualsurvey.model.MTQuestion;
import com.mahindra.annualsurvey.model.MTQuestionType;
import com.mahindra.annualsurvey.model.MTYear;
import com.mahindra.annualsurvey.services.MTAreaService;
import com.mahindra.annualsurvey.services.MTEmployeeService;
import com.mahindra.annualsurvey.services.MTQuestionService;
import com.mahindra.annualsurvey.services.MTQuestionTypeService;
import com.mahindra.annualsurvey.services.MTSurveyConfigService;
import com.mahindra.annualsurvey.services.MTUserService;
import com.mahindra.annualsurvey.services.MTYearService;
import com.mahindra.annualsurvey.utils.FileBean;

@Controller
public class FileDownloadController {

	public static final Logger logger = Logger.getLogger(FileDownloadController.class);

	  /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
             
    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    private String filePath = "/downloads/Employee_Upload_Template.xls";
    private String filePath1 = "/downloads/Question _Upload_templates.xlsx";
     
    /**
     * Method for handling file download request from client
     */
    @RequestMapping(method = RequestMethod.GET,value="/fileDownload")
    public void doDownload(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
 
        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        logger.info("appPath = " + appPath);
        String fullPath = null;
        if("Employee".equalsIgnoreCase(request.getParameter("type")))
        {
        	fullPath =appPath + filePath;
        }
        else {
        	fullPath =appPath + filePath1;
		}
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        logger.info("MIME type: " + mimeType);
 
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        
        inputStream.close();
        inputStream.close();
        outStream.flush();
        outStream.close();
        return ;
    }
 
}
