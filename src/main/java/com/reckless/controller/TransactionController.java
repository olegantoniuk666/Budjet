package com.reckless.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.reckless.businessobject.Transaction;
import com.reckless.config.DateUtils;
import com.reckless.services.TransactionRepository;
import com.reckless.services.TransactionService;

@Controller
@EnableAutoConfiguration
public class TransactionController {
	@Autowired
	private TransactionService transactionService; 
	@Autowired
	private TransactionRepository transactionRepository;
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
     private Date date;
	
	@RequestMapping(value = "/transactionViewer",method = RequestMethod.GET)
	public ModelAndView transactionViewer(){
		ModelAndView model = new ModelAndView();
		List <Transaction> allTransactions = transactionService.getAllTransactions();
		model.addObject("allTransactions", allTransactions);
		model.setViewName("transactionViewer");
		return model;	
	}
	@RequestMapping(value = "/incomeTransactions",method = RequestMethod.GET)
	public ModelAndView income(){
		ModelAndView model = new ModelAndView();
		List <Transaction> allTransactions = transactionService.getIncomeTransactions();
		model.addObject("allTransactions", allTransactions);
		model.setViewName("transactionViewer");
		return model;	
	}
	@RequestMapping(value = "/expenseTransactions",method = RequestMethod.GET)
	public ModelAndView expense(){
		ModelAndView model = new ModelAndView();
		List <Transaction> allTransactions = transactionService.getExpenseTransactions();
		model.addObject("allTransactions", allTransactions);
		model.setViewName("transactionViewer");
		return model;	
	}
	
	@RequestMapping(value = "/addTransaction",method = RequestMethod.POST )
	public void addTransaction(@RequestParam("category")String category,
										@RequestParam("money")int money, 
										@RequestParam("date") @DateTimeFormat(pattern = "yyy-MM-dd")Date date,
										@RequestParam("isIncome")Boolean isIncome,
										HttpServletResponse response) {
		if (date==null){
			date = DateUtils.asDate(LocalDate.now());
		}
		if (!isIncome){
			Transaction transaction = new Transaction(category, money, date);	
			transactionService.addTransaction(transaction);
			
		}else {
			Transaction transaction = new Transaction(category, money, date, isIncome);
			transactionService.addTransaction(transaction);
		}
		
	try {
		response.sendRedirect("/transactionViewer");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@RequestMapping(value = "/removeTransaction", method = RequestMethod.GET)
	public void deleteTransaction(@RequestParam("id")String id,HttpServletResponse response){
		transactionRepository.delete(id);
		try {
			response.sendRedirect("/transactionViewer");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
