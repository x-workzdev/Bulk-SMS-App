package com.xworkz.sms_app.controller;

import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.xworkz.sms_app.service.SMSService;

@Controller
@RequestMapping("/")
public class SMSController {
	
	@Autowired
	private SMSService smsService;

	private Logger logger = LoggerFactory.getLogger(SMSController.class);

	public SMSController() {
		logger.info("{} Is Created...........", this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/sendSMS.do", method = RequestMethod.POST)
	public ModelAndView senBulkSMS(@RequestParam("uploadFile") MultipartFile file, @RequestParam("msg") String msg,String message,@RequestParam("templateID") String templateID) {
		logger.debug("is called....");
		logger.debug("The Template ID entered is {}", templateID);
		ModelAndView modelAndView = new ModelAndView("index");
		if (!file.isEmpty()) {
			logger.debug("contant type {}", file.getContentType());
			List<String> contactList = smsService.getContactListFromXls(file);
			if (Objects.nonNull(contactList)) {
				String response = smsService.sendBulkMSG(contactList,msg,templateID);
				if (Objects.nonNull(response))
				return modelAndView.addObject("msg", "Bulk SMS Sent Successfully");
			 }
			logger.debug("Is file is Empty :{}", contactList.isEmpty());
		} else {
			modelAndView.addObject("msg", "File is not found.........");
		}
		return modelAndView.addObject("msg", "Bulk SMS Sent Faild! Try Again.");
		
	}

	@RequestMapping(value = "/sendSingleSMS.do", method = RequestMethod.POST)
	public ModelAndView sendSingleSMS(@RequestParam("mobile") String mobileNumber,@RequestParam("message") String message,@RequestParam("templateID") String templateID) {
		logger.debug("sendSingleSMS() is called....");
		ModelAndView modelAndView = new ModelAndView("index");
		if (Objects.nonNull(mobileNumber) && Objects.nonNull(message)) {
			logger.debug("The Mobile Number entered is {}", mobileNumber);
			logger.debug("The Template ID entered is {}", templateID);
			try {
				String response = smsService.sendSingleSMS(mobileNumber, message,templateID);
				    if (Objects.nonNull(response)) {
					JSONObject json = new JSONObject(response);
					String  status = json.getString("message");
					JSONArray messageId = json.getJSONArray("message-id");
					logger.debug("messageid={}", messageId.toString());
					return modelAndView.addObject("msg","The "+status+" To: " + mobileNumber + " and The Message Id: " + messageId.toString());
				    }
				    else
					return modelAndView.addObject("msg", "SMS Sent Faild To:" + mobileNumber);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return modelAndView.addObject("msg", "SMS Sent Faild To:" + mobileNumber);

	}

	@RequestMapping(value = "/reports.do", method = RequestMethod.POST)
	public ModelAndView deliveryReports(@RequestParam("messageId") String messageId) {
		logger.debug("deliveryReports() is called....");

		ModelAndView modelAndView = new ModelAndView("index");
		if (messageId != null) {
			try {
				logger.debug("The messageId entered is {}", messageId);
				String response = smsService.deliveryReports(messageId);
				if (Objects.nonNull(response)) {
					JSONObject json = new JSONObject(response);
					String status = json.getString("message_status");
					String mobile = json.getString("mobile");
					logger.debug("mobile number:{} message_status={}", mobile, status);
					return modelAndView.addObject("msg", "Fetched Delivery Reports for " + messageId + " The Mobile: "+ mobile + " And Status: " + status);
				} else {
					return modelAndView.addObject("msg","Delivery Reports Not available for the Message Id " + messageId);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return modelAndView.addObject("msg", "Delivery Reports Not available for the Message Id " + messageId);
	}

	@RequestMapping(value = "/checkSMSBalance.do", method = RequestMethod.POST)
	public ModelAndView checkSMSBalance() {
		ModelAndView modelAndView = new ModelAndView("index");
		try {
			logger.debug("checkSMSBalance() is called....");
			logger.debug("Fecthing Balance...");
			String result = smsService.checkSMSBalance();
			if (Objects.nonNull(result))
				return modelAndView.addObject("msg", "Total SMS Balance Left: " + result);
			else
				return modelAndView.addObject("msg", "Sorry couldn't able to fecth Balace at this time!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return modelAndView.addObject("msg", "Sorry couldn't able to fecth Balace at this time!");
	}
}
