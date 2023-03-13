package com.mahindra.annualsurvey.services;

import org.springframework.stereotype.Service;


import com.mahindra.annualsurvey.model.MTLoginHistory;


public interface LoginUserService {

public void createLoginHistory(MTLoginHistory loginHistory);
}
