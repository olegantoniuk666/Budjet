package com.reckless.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.reckless.Application;
import com.reckless.services.CostItemService;
//import com.reckless.services.RepositoriesPackage;
import com.reckless.services.SimpleCostItemService;
import com.reckless.services.SimpleTransactionService;
import com.reckless.services.TransactionService;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackageClasses = { Application.class })

public class SpringContainerConfiguration extends AbstractMongoConfiguration {

	@Autowired
	Environment environment;

	@Override
	public String getDatabaseName() {
		return environment.getProperty("mongodb.db");
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(environment.getProperty("mongodb.host"), Integer.valueOf(environment.getProperty("mongodb.port")));
	}
	
	@Bean
	public CostItemService costItemService() {
		return new SimpleCostItemService();
	}

	@Bean
	public TransactionService transactionService() {
		return new SimpleTransactionService();
	}
}