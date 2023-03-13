package com.mahindra.annualsurvey.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileBean {

	private CommonsMultipartFile fileData;

	private String name;

	private String year;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
