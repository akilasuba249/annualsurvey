/**
 * 
 */
package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.dto.AreaDTO;

/**
 * @author Yash Mahale
 * 
 */
public interface MTAreaService {

	public String create(AreaDTO area);

	public long select(String areaDesc);

	public List<AreaDTO> getArea();

	public List<AreaDTO> selectAreas(String area);

	public String updateArea(AreaDTO area);

	public String deleteArea(String areaCode);

}
