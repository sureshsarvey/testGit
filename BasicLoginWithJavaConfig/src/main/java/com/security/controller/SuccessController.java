package com.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SuccessController {
	
	
	@RequestMapping(value="/login",method= RequestMethod.GET)
	public ModelAndView getPage(){
		System.out.println("requested for loginpage");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/check", method= RequestMethod.POST)
	public ModelAndView getSuccessPage(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		System.out.println("request for authentication");
		return new ModelAndView("success");
	}
	
	@RequestMapping("/success")
	public ModelAndView getSuccessPage(){
		return new ModelAndView("success");
	}
	
	@RequestMapping("/homepage")
	public ModelAndView getHomePage(){
		System.out.println("requested for homepage");
		return new ModelAndView("homepage");
	}
	
	@RequestMapping("/admin")
	public ModelAndView getAdminPage(){
		System.out.println("requested for homepage");
		return new ModelAndView("admin");
	}

	
	

}
