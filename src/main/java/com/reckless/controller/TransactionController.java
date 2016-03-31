package com.reckless.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.reckless.businessobject.Transaction;
import com.reckless.services.TransactionService;

@Controller
@EnableAutoConfiguration
public class TransactionController {
	@Autowired
	private TransactionService transactionService; 
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
     private Date date;
	
	@RequestMapping(value = "/transactionViewer",method = RequestMethod.GET)
	public String transactionViewer(){
		return "transactionViewer";	
	}
	
	@RequestMapping(value = "/addTransaction",method = RequestMethod.POST )
	public String addTransaction(@RequestParam("category")String category,
										@RequestParam("money")int money, 
										@RequestParam("date") @DateTimeFormat(pattern = "yyy-MM-dd")Date date,
										@RequestParam("isIncome")Boolean isIncome){
		if (!isIncome){
			Transaction transaction = new Transaction(category, money, date);	
			transactionService.addTransaction(transaction);
			
		}else {
			Transaction transaction = new Transaction(category, money, date, isIncome);
			transactionService.addTransaction(transaction);
		}
		
		return "transactionViewer";
		
	}
	
}
