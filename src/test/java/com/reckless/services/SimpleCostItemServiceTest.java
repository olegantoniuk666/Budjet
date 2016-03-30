package com.reckless.services;

import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.reckless.businessobject.CostItem;
import com.reckless.businessobject.Transaction;
import com.reckless.config.SpringContainerConfiguration;

@EnableAutoConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContainerConfiguration.class })
public class SimpleCostItemServiceTest {

	@Autowired
	private CostItemService costItemService;
	@Autowired
	private CostItemRepository costItemRepository;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private TransactionRepository transactionRepository;
	
	private CostItem testCostItem;
	private Transaction income;
	
	@Before
	public void setupDB(){
		costItemRepository.deleteAll();
		transactionRepository.deleteAll();
		income = new Transaction("income",1000,new Date(),true);
		transactionService.addTransaction(income);
		testCostItem = new CostItem("Automobile", 20);
		costItemService.addCostItem(testCostItem);
		Transaction audi = new Transaction("Automobile", 250, new Date());
		transactionService.addTransaction(audi);
		
	}
	@Test
	public void whenAdding_newCostItemCanBeRead() {
		CostItem actualCostItem =  costItemService.getCostItem("Automobile");
		Assertions.assertThat(actualCostItem).isEqualTo(testCostItem);
	}
	@Test
	public void when_CostItemRemoveByCategoty(){
		
		costItemService.removeByCategory("Automobile");
		Assertions.assertThat(costItemService.getAllCostItems().isEmpty());
	}
	@Test
	public void whenPlanByAllCostItemsCanBeRead(){
		Assertions.assertThat(costItemService.getPercentByAllCostItems()).isEqualTo(20);
		
	}
	@Test
	public void whenGettingPlanByCategory(){
		Assertions.assertThat(costItemService.getPlanByCategory("Automobile")).isEqualTo(200);
	}
	@Test
	public void whenGettingFaktByCategory(){
		
		Assertions.assertThat(costItemService.getFactByCategory("Automobile")).isEqualTo(250.0);
	}
	@Test
	public void whenGettingBalanceByCategory(){
		Assertions.assertThat(costItemService.getBalanceByCategory("Automobile")).isEqualTo(-50.0);
		
	}
}
