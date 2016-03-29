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
	@Autowired
	Environment environment;
	@Autowired
	Mongo mongo;

	@Before
	public void cleanDB() {
		//mongo.dropDatabase((environment.getProperty("mongodb.db")));
		transactionRepository.deleteAll();
		
	}

	public Date date = new Date(1986, 7, 20);
	public LocalDate localDate = LocalDate.now();
	
	@Test
	public void whenAddedTransactionCanBeReadByCategory() {
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setCategory("Food");
		transactionService.addTransaction(transaction);
		List<Transaction> sortedByCategory = transactionService
				.getTransactionsByCategory("Food");
		for (Transaction actualTransaction : sortedByCategory) {
			Assertions.assertThat(transaction.getCategory()).isEqualTo(
					actualTransaction.getCategory());
		}

	}
	@Test
	public void whenAddedTransactionCanBeReadByDate() {
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setCategory("alcohol");
		transactionService.addTransaction(transaction);
		List<Transaction> sortedByDate = transactionService
				.getTransactionsByDate(date);

		for (Transaction trans : sortedByDate) {
			Assertions.assertThat(trans.getDate().equals(date));
		}

	}
	@Test
	public void whenAddedTransactionCanBeReadByLocalDate() {
		Transaction transaction = new Transaction();
		transaction.setLocalDate(localDate);
		transaction.setCategory("Drugs");
		transactionService.addTransaction(transaction);
		List<Transaction> sortedByLocalDate = transactionService
				.getTransactionsByLocalDate(localDate);
		for (Transaction trans : sortedByLocalDate) {
			Assertions.assertThat(trans.getLocalDate().isEqual(localDate));
		}

	}
	@Test
	public void whenDeletCreatedTransaction() {

		Transaction transaction = new Transaction();
		transaction.setCategory("GansTransaction");
		transaction.setDate(date);
		transactionService.addTransaction(transaction);
		transactionService.deleteTransaction(transaction);
		Assertions.assertThat(transactionRepository.findAll().isEmpty());
	}
	
	@Test 
	public void whenGettingTransactionQuantityByIncome(){
		Transaction gansTransaction = new Transaction("Gans", 50, date, true);
		Transaction kateTransaction = new Transaction("Kate", 50, date);
		transactionService.addTransaction(gansTransaction);
		transactionService.addTransaction(kateTransaction);
		int allIncome = transactionService.getIncomeTransactionsQuantity();
		Assertions.assertThat(transactionService.getIncomeTransactionsQuantity()).isEqualTo(50);
		//Assert.assertEquals(allIncome, 50);
		
		
	}
	@Test
	public void whenGettingExpenseTransactionsQuantity(){
		Transaction gansTransaction = new Transaction("GansExpances",100, date);
		Transaction kateTransaction = new Transaction("KateExpances", 150, date);
		transactionService.addTransaction(gansTransaction);
		transactionService.addTransaction(kateTransaction);
		Assertions.assertThat(transactionService.getExpenseTransactionsQuantity()).isEqualTo(250);
	}
	@Test
	public void whenGettingAllTransactions(){
		for(int i=1;i<21;i++){
			Transaction tr = new Transaction();
			transactionService.addTransaction(tr);
		}
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		allTransactions = transactionService.getAllTransactions();
		Assertions.assertThat(allTransactions.size()).isEqualTo(20);
	}
	
	@Test
	public void whenGettingAllIncomeTransactions(){
		for(int i =1;i<11;i++){
			Transaction incomeTransaction = new Transaction("GansSalary"+i,100,date,true);
			Transaction expanceTransaction = new Transaction("KateExpance"+i,100,date);
			transactionService.addTransaction(incomeTransaction);
			transactionService.addTransaction(expanceTransaction);
		}
		List <Transaction> incomeTransactions = new ArrayList<Transaction>();
				incomeTransactions = transactionService.getIncomeTransactions();
				Assertions.assertThat(incomeTransactions.size()).isEqualTo(10);
	}
	@Test
	public void whenGettingAllExpenseTransactions(){
		for(int i =1;i<11;i++){
			Transaction incomeTransaction = new Transaction("GansSalary"+i,100,date,true);
			Transaction expanceTransaction = new Transaction("KateExpance"+i,100,date);
			transactionService.addTransaction(incomeTransaction);
			transactionService.addTransaction(expanceTransaction);
		}
		List<Transaction>expenseTransactions = new ArrayList<Transaction>();
		expenseTransactions = transactionService.getExpenseTransactions();
		Assertions.assertThat(expenseTransactions.size()).isEqualTo(10);
	}
}