package com.xworkz.sms_app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface SMSService {

	public List<String> getContactListFromXls(MultipartFile file);

	public String sendBulkMSG(List<String> contactList, String message, String templateId);
	
	public String sendSingleSMS(String mobileNumber,String msg, String templateId);

	public String deliveryReports(String messageId);
	
	public String checkSMSBalance();
}
