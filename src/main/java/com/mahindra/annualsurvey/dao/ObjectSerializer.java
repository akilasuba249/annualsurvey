package com.mahindra.annualsurvey.dao;

import java.util.Iterator;
import java.util.List;

import com.mahindra.annualsurvey.model.AreaEmpMapping;
import com.mahindra.annualsurvey.model.MTRespSubQuestDtls;

public class ObjectSerializer {

	public String getAreaCodeNames(List<AreaEmpMapping> list, String delimiter) {

		Iterator<AreaEmpMapping> iterator = list.iterator();
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			AreaEmpMapping e= iterator.next();
			sb.append(" ");
			sb.append(e.getEmployeeArea().getAreaCode());
			sb.append(delimiter);
			sb.append(e.getAreaCode());
			sb.append(delimiter);
			
		}
		if (sb.toString().length() > 0)
			return sb.toString().length() > 0 ? sb.toString().substring(0,
					sb.toString().length() - 1) : "";
		else
			return "--";
	}

	public String getSubquestRespNames(List<MTRespSubQuestDtls> list, String delimiter) {

		Iterator<MTRespSubQuestDtls> iterator = list.iterator();
		StringBuffer sb = new StringBuffer();
		while (iterator.hasNext()) {
			MTRespSubQuestDtls e= iterator.next();
			sb.append("");
			sb.append(e.getSubquestionId());
			sb.append(delimiter);
			sb.append(e.getAnswerNo());
			sb.append(delimiter);
			
		}
		if (sb.toString().length() > 0)
			return sb.toString().length() > 0 ? sb.toString().substring(0,
					sb.toString().length() - 1) : "";
		else
			return "--";
	}
}
