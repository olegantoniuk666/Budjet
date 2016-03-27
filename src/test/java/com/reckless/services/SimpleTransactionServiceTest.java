package com.reckless.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;

import com.reckless.Application;
import com.reckless.businessobject.Transaction;
import com.reckless.config.DateUtils;
import com.reckless.config.SpringContainerConfiguration;

@EnableAutoConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringContainerConfiguration.class })
public class SimpleTransactionServiceTest {

	@Autowired
	private TransactionService transactionService;
	
	public Date date = new Date(1986,7,20);
	public LocalDate localDate = LocalDate.now();

	
	@Test
	public void whenAddedTransactionCanBeReadByCategory() {
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setCategory("Food");
		transactionService.addTransaction(transaction);

		List<Transaction> sortedByCategory = transactionService.getTransactionsByCategory("Food");
		for (Transaction actualTransaction : sortedByCategory) {
			Assertions.assertThat(transaction.getCategory()).isEqualTo(actualTransaction.getCategory());
		}

	}
	@Test
	public void whenAddedTransactionCanBeReadByDate(){
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setCategory("alcohol");
		transactionService.addTransaction(transaction);
		List<Transaction>sortedByDate = transactionService.getTransactionsByDate(date);
		
		for(Transaction trans:sortedByDate){
			Assertions.assertThat(trans.getDate().equals(date));
		}
		

		
	}
	@Test
	public void whenAddedTransactionCanBeReadByLocalDate(){
		Transaction transaction = new Transaction();
		transaction.setLocalDate(localDate);
		transaction.setCategory("Drugs");
		transactionService.addTransaction(transaction);
		List<Transaction>sortedByLocalDate = transactionService.getTransactionsByLocalDate(localDate);
		for(Transaction trans: sortedByLocalDate){
			Assertions.assertThat(trans.getLocalDate().isEqual(localDate));
		}
		

		
	}

	
}
