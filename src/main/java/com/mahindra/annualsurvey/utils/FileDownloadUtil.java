package com.mahindra.annualsurvey.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class FileDownloadUtil {
	
	public void downloadfile(HttpServletRequest request ,HttpServletResponse response ,String filepath) throws Exception { 
		
//		 String fullPath = "C:\\NTC\\MLU_Registration_Form_Template.xlsx";
		 
		 System.out.println("Into get Jasper report");
       // get absolute path of the application
       String appPath=request.getSession().getServletContext().getRealPath("");
       ServletContext context =request.getSession().getServletContext();
       System.out.println("appPath = " + appPath);
       
       System.out.println("Path is "+filepath);

       filepath.replace("/", "//");     
       
       System.out.println("Path is "+filepath);
      

       File downloadFile = new File(filepath);
       FileInputStream inputStream = new FileInputStream(downloadFile);
        
       String mimeType = context.getMimeType(filepath);
       if (mimeType == null) {
           mimeType = "application/octet-stream";
       }
       System.out.println("MIME type: " + mimeType);
       

       response.setContentType(mimeType);
       response.setContentLength((int) downloadFile.length());

       // set headers for the response
       String headerKey = "Content-Disposition";
       String headerValue = String.format("attachment; filename=\"%s\"",
               downloadFile.getName());
       response.setHeader(headerKey, headerValue);

       // get output stream of the response
       OutputStream outStream = response.getOutputStream();

       byte[] buffer = new byte[4096];
       int bytesRead = -1;

       // write bytes read from the input stream into the output stream
       while ((bytesRead = inputStream.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
       }
       
       inputStream.close();
       inputStream.close();
       outStream.flush();
       outStream.close();
}
	

}
