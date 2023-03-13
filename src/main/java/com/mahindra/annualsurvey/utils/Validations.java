package com.mahindra.annualsurvey.utils;


public class Validations {
	
	public static boolean isEmailValidate(String email) {

		if (email != null && !email.equals("")) {
			String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-]+\\.)+[\\w]+[\\w]$";
			Boolean b = email.matches(EMAIL_REGEX);
			b = email.matches(EMAIL_REGEX);
			return b;
		} else {
			return false;
		}

	}
	
/*	public static void makeRowBold(Workbook wb, Row row){
	    CellStyle style = wb.createCellStyle();//Create style
	    Font font = wb.createFont();//Create font
	    font.setBold(true);
	    font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
	    style.setFont(font);//set it to bold

	    for(int i = 0; i < row.getLastCellNum(); i++){//For each cell in the row 
	        row.getCell(i).setCellStyle(style);//Set the sty;e
	    }
	}*/
	
	/*public static void main(String[] args ){
		
		System.out.println(isEmailValidate("katkar.ravindra@mahindra.com"));
	}*/
	
	

}
