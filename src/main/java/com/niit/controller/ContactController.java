package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.ContactUsDao;
import com.niit.model.ContactUs;

@Controller
public class ContactController {

	@Autowired
	ContactUsDao contactUsDAO;
	
	@RequestMapping(value="/send_mail",method=RequestMethod.POST)
	public ModelAndView addContactUs(@RequestParam("fname") String fname,@RequestParam("lname") String lname, @RequestParam("email") String email,@RequestParam("mobile") long mobile,@RequestParam("message") String message){
		ModelAndView m=new ModelAndView("redirect:contact_us");
		ContactUs contactUs=new ContactUs();
		contactUs.setFname(fname);
		contactUs.setLname(lname);
		contactUs.setEmail(email);
		contactUs.setMobile(mobile);
		contactUs.setMessage(message);
		contactUsDAO.insertOrUpdateContactUs(contactUs);
		return m;
	}
}
