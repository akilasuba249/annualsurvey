package com.mahindra.annualsurvey.controllers;

import java.awt.Color
;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mahindra.annualsurvey.captcha.Util;

@Controller
public class CaptchaGenServlet extends HttpServlet {

         public static final String FILE_TYPE = "jpeg";

       
         
     	@RequestMapping(method = RequestMethod.GET,value="captcha")
        protected void doPost(HttpServletRequest request, HttpServletResponse response,Model model)
                        throws ServletException, IOException {

            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Max-Age", 0);
            response.setContentType("image/jpeg");

        	String captchaStr="";
        	
        	 	
        	captchaStr=Util.generateCaptchaTextMethod2(3);
            	        
        		
            try {

            	int width=100;     	int height=40;
            	
            	Color bg = new Color(227,24,55);
            	Color fg = new Color(225,225,225);
            	
            	Font font = new Font("Arial", Font.ITALIC, 20);
            	BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
            	Graphics g = cpimg.createGraphics();

            	g.setFont(font);
                g.setColor(bg);
                g.fillRect(0, 0, width, height);
                g.setColor(fg);
            	g.drawString(captchaStr,10,25);   
            	
                HttpSession session = request.getSession(true);
                session.setAttribute("CAPTCHA", captchaStr);

               OutputStream outputStream = response.getOutputStream();
               model.addAttribute("captchatesxt", captchaStr)  ;  
               ImageIO.write(cpimg, FILE_TYPE, outputStream);
               outputStream.close();
               
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }



        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        	doPost(request, response);
        }

  

}
