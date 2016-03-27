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

	public int getTransactionSumByIncome() {
		List<Transaction> incomeTransaction = new ArrayList<Transaction>();
		incomeTransaction = transactionRapository.findAll();
		Iterator<Transaction> iter = incomeTransaction.iterator();
		int incomeSum = 0;
		while (iter.hasNext()) {
			Transaction transaction = iter.next();
			if (transaction.getIsIncome().equals(true)) {
				incomeSum = incomeSum + transaction.getQuantity();
			} else {
				continue;
			}
		}
		return incomeSum;
	}

	public int getTransactionSumWithoutIncome() {
		List<Transaction> incomeTransaction = new ArrayList<Transaction>();
		incomeTransaction = transactionRapository.findAll();
		Iterator<Transaction> iter = incomeTransaction.iterator();
		int withoutIncomeSum = 0;
		while (iter.hasNext()) {
			Transaction transaction = iter.next();
			if (transaction.getIsIncome().equals(false)) {
				withoutIncomeSum = withoutIncomeSum + transaction.getQuantity();
			} else {
				continue;
			}
		}
		return withoutIncomeSum;
	}
}
