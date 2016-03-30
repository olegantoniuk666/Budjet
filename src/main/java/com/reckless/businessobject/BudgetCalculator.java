package com.reckless.businessobject;

import org.springframework.beans.factory.annotation.Autowired;

import com.reckless.services.CostItemService;
import com.reckless.services.TransactionService;

public class BudgetCalculator {
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private  CostItemService costItemService;
	
	private int balance;
	
	private int plan; 
	
	private int fact;

	public int getBalance() {
		return transactionService.getIncomeTransactionsQuantity() - 
				transactionService.getExpenseTransactionsQuantity();
	}

	

}
