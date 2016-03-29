package com.reckless.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reckless.businessobject.Transaction;

public class SimpleTransactionService implements TransactionService {

	@Autowired
	private TransactionRepository transactionRapository;

	public void addTransaction(Transaction transaction) {
		transactionRapository.save(transaction);
	}

	public List<Transaction> getAllTransactions() {
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		allTransactions = transactionRapository.findAll();
		return allTransactions;
	}

	public List<Transaction> getTransactionsByCategory(String category) {
		List<Transaction> sortedByCategory = new ArrayList<Transaction>(100);
		sortedByCategory = transactionRapository.findAll();
		Iterator<Transaction> iter = sortedByCategory.iterator();
		while (iter.hasNext()) {
			Transaction transaction = iter.next();
			if ((transaction.getCategory()).equals(null)) {
				iter.remove();
			}
			if (!(transaction.getCategory().equals(category))) {
				iter.remove();
			}
		}
		return sortedByCategory;
	}

	public List<Transaction> getTransactionsByDate(Date date) {
		List<Transaction> sortedByDate = new ArrayList<Transaction>(100);
		sortedByDate = transactionRapository.findAll();
		Iterator<Transaction> iter = sortedByDate.iterator();
		while (iter.hasNext()) {
			Transaction trans = iter.next();
			if (trans.getDate().equals(null)) {
				iter.remove();
			}
			if (!(trans.getDate().equals(date))) {
				iter.remove();
			}
		}
		return sortedByDate;
	}

	public List<Transaction> getTransactionsByLocalDate(LocalDate localDate) {
		List<Transaction> sortedByLocalDate = new ArrayList<Transaction>(100);
		sortedByLocalDate = transactionRapository.findAll();
		Iterator<Transaction> iter = sortedByLocalDate.iterator();
		while (iter.hasNext()) {
			Transaction trans = iter.next();
			if (trans.getLocalDate().equals(null)) {
				iter.remove();
			}
			if (!(trans.getLocalDate().equals(localDate))) {
				iter.remove();
			}
		}
		return sortedByLocalDate;

	}

	public void deleteTransaction(Transaction transaction) {
		transactionRapository.delete(transaction);

	}

	public int getIncomeTransactionsQuantity() {
		List<Transaction> incomeTransactions = new ArrayList<Transaction>();
		int sum = 0;
		incomeTransactions = transactionRapository.findAll();
		Iterator<Transaction> iter = incomeTransactions.iterator();
		while (iter.hasNext()) {
			Transaction transaction = iter.next();
			if (transaction.getIsIncome()==false) {
				iter.remove();
			} 
		}
		
		for(Transaction transaction:incomeTransactions){
			sum = sum+transaction.getQuantity();
		}
		System.out.println(sum);
		return sum;
		
	}

	public int getExpenseTransactionsQuantity() {
		List<Transaction> expenseTransactions = new ArrayList<Transaction>();
		int sum = 0;
		expenseTransactions = transactionRapository.findAll();
		Iterator<Transaction> iter = expenseTransactions.iterator();
		while (iter.hasNext()) {
			Transaction transaction = iter.next();
			if (transaction.getIsIncome()) {
				iter.remove();
			} else{continue;}
		}
		
		for(Transaction transaction:expenseTransactions){
			sum = sum + transaction.getQuantity();
		}
		return sum;
	
	}

	public List<Transaction> getIncomeTransactions() {
		List<Transaction> incomeTransactions = new ArrayList<Transaction>();
		incomeTransactions = transactionRapository.findAll();
		Iterator<Transaction> iterator = incomeTransactions.iterator();
		while (iterator.hasNext()) {
			Transaction iterTransaction = iterator.next();
			if (iterTransaction.getIsIncome()==false) {
				iterator.remove();
			} 

		}
		return incomeTransactions;
	}

	public List<Transaction> getExpenseTransactions() {
		List<Transaction> expenseTransactions = new ArrayList<Transaction>();
		expenseTransactions = transactionRapository.findAll();
		Iterator<Transaction> iterator = expenseTransactions.iterator();
		while (iterator.hasNext()) {
			Transaction iterTransaction = iterator.next();
			if (iterTransaction.getIsIncome()) {
				iterator.remove();
			}

		}
		return expenseTransactions;
	}

}
