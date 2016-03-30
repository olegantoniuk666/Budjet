package com.reckless.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import com.mongodb.Mongo;
import com.reckless.Application;
import com.reckless.businessobject.Transaction;
import com.reckless.config.DateUtils;
import com.reckless.config.SpringContainerConfiguration;

@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContainerConfiguration.class })
public class SimpleTransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private TransactionRepository transactionRepository;

	
	private Date date = new Date(1986, 7, 20);
	private LocalDate localDate = LocalDate.now();
	private Transaction incomeTransaction = new Transaction("gansSalary",1000,localDate,true);
	private Transaction expenseTransaction = new Transaction("food", 100, date);
	
	@Before
	public void setupDB() {
		transactionRepository.deleteAll();
		transactionService.addTransaction(expenseTransaction);
		transactionService.addTransaction(incomeTransaction);
	}

	
	@Test
	public void whenAddedTransactionCanBeReadByCategory() {
		List<Transaction> sortedByCategory = transactionService
				.getTransactionsByCategory("food");
		Assertions.assertThat(sortedByCategory.size()).isEqualTo(1);
		for (Transaction actualTransaction : sortedByCategory) {
			Assertions.assertThat(actualTransaction.getCategory()).isEqualTo(expenseTransaction.getCategory());
		}
		
	}
	@Test
	public void whenAddedTransactionCanBeReadByDate() {
		List<Transaction> sortedByDate = transactionService
				.getTransactionsByDate(date);
		Assertions.assertThat(sortedByDate.size()).isEqualTo(1);
		for (Transaction trans : sortedByDate) {
			Assertions.assertThat(trans.getDate().equals(date));
		}

	}
	@Test
	public void whenAddedTransactionCanBeReadByLocalDate() {
		List<Transaction> sortedByLocalDate = transactionService
				.getTransactionsByLocalDate(localDate);
		Assertions.assertThat(sortedByLocalDate.size()).isEqualTo(1);
		for (Transaction trans : sortedByLocalDate) {
			System.out.println();
			System.out.println(trans.toString());
			System.out.println();
			Assertions.assertThat(trans.getLocalDate().isEqual(localDate));
		}

	}
	@Test
	public void whenCanDeleteCreatedTransaction() {
		transactionService.deleteTransaction(incomeTransaction);
		transactionService.deleteTransaction(expenseTransaction);
		Assertions.assertThat(transactionRepository.count()).isEqualTo(0);
	}	
	@Test 
	public void whenGettingTransactionQuantityByIncome(){
		int allIncome = transactionService.getIncomeTransactionsQuantity();
		Assertions.assertThat(transactionService.getIncomeTransactionsQuantity()).isEqualTo(1000);
	}
	@Test
	public void whenGettingExpenseTransactionsQuantity(){
		Assertions.assertThat(transactionService.getExpenseTransactionsQuantity()).isEqualTo(100);
	}
	@Test
	public void whenGettingAllTransactions(){
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		allTransactions = transactionService.getAllTransactions();
		Assertions.assertThat(allTransactions.size()).isEqualTo(2);
	}
	@Test
	public void whenGettingAllIncomeTransactions(){
		List <Transaction> incomeTransactions = new ArrayList<Transaction>();
				incomeTransactions = transactionService.getIncomeTransactions();
				Assertions.assertThat(incomeTransactions.size()).isEqualTo(1);
	}
	@Test
	public void whenGettingAllExpenseTransactions(){
		List<Transaction>expenseTransactions = new ArrayList<Transaction>();
		expenseTransactions = transactionService.getExpenseTransactions();
		Assertions.assertThat(expenseTransactions.size()).isEqualTo(1);
	}
}