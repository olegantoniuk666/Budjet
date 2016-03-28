package com.reckless.services;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.reckless.businessobject.Transaction;


public interface TransactionRepository extends MongoRepository<Transaction, String> {
public void delete(Transaction entity);

}
