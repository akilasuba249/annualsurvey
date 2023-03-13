package com.mahindra.annualsurvey.dao;

import java.util.List;

import com.mahindra.annualsurvey.model.MTArea;

public interface MTAreaDao extends CommonDao<MTArea> {
	
	     
	    public void save(MTArea area);
	     
	    public void delete(int areaCode);
	     
	    public MTArea get();
	     
	    public List<MTArea> list();

		public List<MTArea> selectAreas(String query);

	}

