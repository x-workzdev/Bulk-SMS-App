package com.xworkz.sms_app.dao;

public interface SMSdao {
	public String sendSMS(String apiKey, String username, String sender, String phone, String message,
			String smsType,String route,String templateId);
	
	public String deliveryReports(String apiKey, String username, String apiRequest ,String messageId);
	
	public String checkSMSBalance(String apiKey, String username, String apiRequest ,String route);
}
