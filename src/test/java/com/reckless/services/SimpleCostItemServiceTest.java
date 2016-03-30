package com.reckless.services;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import com.mongodb.MongoClient;
import com.reckless.businessobject.CostItem;
import com.reckless.config.SpringContainerConfiguration;

@EnableAutoConfiguration

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContainerConfiguration.class })
public class SimpleCostItemServiceTest {

	@Autowired
	private CostItemService costItemService;
	@Autowired
	private CostItemRepository costItemRepository;
	
	private CostItem testCostItem;
	
	@Before
	public void setupDB(){
		costItemRepository.deleteAll();
		testCostItem = new CostItem("Automobile", 20);
		costItemService.addCostItem(testCostItem);
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
	
}
