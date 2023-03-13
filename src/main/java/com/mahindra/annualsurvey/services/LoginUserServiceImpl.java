package com.mahindra.annualsurvey.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahindra.annualsurvey.dao.LoginUserDao;
import com.mahindra.annualsurvey.model.MTLoginHistory;

@Service
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	LoginUserDao loginUserDao;
	@Override
	@Transactional
	public void createLoginHistory(MTLoginHistory loginHistory) {
		loginHistory.setLoginOn(new Date());
		loginUserDao.create(loginHistory);
		

	}

}
