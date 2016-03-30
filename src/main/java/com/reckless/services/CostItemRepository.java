package com.reckless.services;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.reckless.businessobject.CostItem;


public interface CostItemRepository extends MongoRepository<CostItem, String> {
	public List<CostItem> getByCategory(String category); 

}
