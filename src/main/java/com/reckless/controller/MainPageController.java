package com.reckless.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.reckless.businessobject.CostItem;
import com.reckless.services.TransactionService;

@Controller
@EnableAutoConfiguration
public class MainPageController {
	@Autowired
	TransactionService transactionService; 

	@RequestMapping(value = "/mainPage",method = RequestMethod.GET)
	public ModelAndView mainPage(){
		ModelAndView model = new ModelAndView();
		int dohod = transactionService.getIncomeTransactionsQuantity();
		int rashod = transactionService.getExpenseTransactionsQuantity();
		model.addObject("dohod", dohod);
		model.addObject("rashod", rashod);
		model.setViewName("mainPage");
		
		return model;
	}
	
}
