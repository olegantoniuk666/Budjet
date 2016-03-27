package com.reckless.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reckless.businessobject.CostItem;

@Controller
@EnableAutoConfiguration
public class MainPageController {

	@RequestMapping(value = "/mainPage",method = RequestMethod.GET)
	public String mainPage(){
		return "mainPage";
	}
		@RequestMapping(value = "/osnovnoe",method = RequestMethod.GET)
		public String osnovnoe(){
			return "osnovnoe";	
	}
		@RequestMapping(value = "/diary",method = RequestMethod.GET)
		public String diary(){
			return "diary";
				
		
	}
}
