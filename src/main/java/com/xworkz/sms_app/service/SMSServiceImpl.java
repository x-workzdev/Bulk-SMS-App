package com.xworkz.sms_app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.xworkz.sms_app.dao.SMSdao;
import com.xworkz.sms_app.util.EncryptionHelper;
import com.xworkz.sms_app.util.ExcelHelper;

@Service
public class SMSServiceImpl implements SMSService {

	static Logger logger = LoggerFactory.getLogger(SMSServiceImpl.class);
	@Autowired
	ExcelHelper excelHelper;
	@Autowired
	SMSdao smsdao;
	@Autowired
	EncryptionHelper encryptionHelper;
	@Value("${apiKey}")
	private String apiKey;
	@Value("${SMSusername}")
	private String SMSusername;
	@Value("${sender}")
	private String sender;
	@Value("${simpleSMS}")
	private String simpleSMS;
	@Value("${credit}")
	private String creditCheck;
	@Value("${route}")
	private String route;
	@Value("${report}")
	private String report;
	
	public List<String> getContactListFromXls(MultipartFile file) {
		if (!file.isEmpty()) {
			List<String> contactList = null;
			try {
				file.getBytes();
				String filename = file.getOriginalFilename();
				// Creating the directory to store file
				logger.info("File name is {}", filename);

				if ((file.getInputStream() != null)) {
					contactList = excelHelper.getContactListFromInputStream(file.getInputStream());
				}
				return contactList;
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return null;
	}

	public String sendBulkMSG(List<String> contactList, String message,String templateId) {
		logger.info(contactList.toString());
		String res = null;
		for (String phone : contactList) {
			if (phone.length() != 0) {
				try {
					Thread.sleep(1000);
					res = smsdao.sendSMS(encryptionHelper.decrypt(apiKey), encryptionHelper.decrypt(SMSusername),encryptionHelper.decrypt(sender),
							phone, message,simpleSMS, route, templateId);
					logger.info("BulkMSG Result is {}", res);
				} catch (Exception e) {
					logger.error("\n\nMessage is {} and exception is {}\n\n\n\n\n", e.getMessage(), e);
				}

			}
		}
		return res;
	}

	@Override
	public String sendSingleSMS(String mobileNumber, String message, String templateId) {
		try {
			logger.debug("smsType is :{} mobileNumber is :{} message is: {}",simpleSMS,mobileNumber,message);
			String res = smsdao.sendSMS(encryptionHelper.decrypt(apiKey), encryptionHelper.decrypt(SMSusername),encryptionHelper.decrypt(sender),
					 mobileNumber, message,simpleSMS, route,templateId);
			logger.info("SingleSMS Result is {}", res);
			return res;
		} catch (Exception e) {
			logger.error("\n\nMessage is {} and exception is {}\n\n\n\n\n", e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String deliveryReports(String messageId) {
		try {
			String res = smsdao.deliveryReports(encryptionHelper.decrypt(apiKey),
					encryptionHelper.decrypt(SMSusername), report, messageId);
			logger.info("Delivery Reports Result is {}", res);
			return res;
		} catch (Exception e) {
			logger.error("\n\nMessage is {} and exception is {}\n\n\n\n\n", e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String checkSMSBalance() {
		try {
			/*
			 * logger.debug("API KEY is {} SMSusername Key is {} Sender id is {}",apiKey,
			 * SMSusername,sender);
			 * 
			 * logger.
			 * debug("API KEY is {} SMSusername Key is {} Sender id is {} report is {}",
			 * encryptionHelper.decrypt(apiKey), encryptionHelper.decrypt(SMSusername),
			 * encryptionHelper.decrypt(sender), report);
			 */

			logger.debug("API KEY is {}", encryptionHelper.decrypt(apiKey));
			String result = smsdao.checkSMSBalance(encryptionHelper.decrypt(apiKey), encryptionHelper.decrypt(SMSusername), creditCheck, route);
			logger.info("checkSMSBalance Result is {}", result);
			return result;
		} catch (Exception e) {
			logger.error("\n\nMessage is {} and exception is {}\n\n\n\n\n", e.getMessage(), e);
		}
		return null;
	}
}
