package com.reckless.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.reckless.businessobject.Transaction;

public interface TransactionService {
	public void addTransaction(Transaction transaction);

	public List<Transaction> getTransactionsByCategory(String category);

	public List<Transaction> getTransactionsByLocalDate(LocalDate localdate);

	public List<Transaction> getTransactionsByDate(Date date);

	public List<Transaction> getAllTransactions();

	public void deleteTransaction(Transaction transaction);

	public int getIncomeTransactionsQuantity();

	public int getExpenseTransactionsQuantity();
	
	public List<Transaction> getIncomeTransactions(); 
	
	public List<Transaction> getExpenseTransactions();

}
