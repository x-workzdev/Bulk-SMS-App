package com.xworkz.sms_app.service;

import com.xworkz.sms_app.dto.LoginDTO;

public interface LoginService {

	public boolean generateOTP();
	
	public String genarateRandomOTP();

	public boolean validateAndLogin(LoginDTO dto);

}
