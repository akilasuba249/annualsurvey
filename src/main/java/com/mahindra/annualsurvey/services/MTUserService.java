package com.mahindra.annualsurvey.services;

import java.util.List;

import com.mahindra.annualsurvey.model.MTUser;

public interface MTUserService
{
	public List<MTUser> checkLogin(MTUser user);

}
