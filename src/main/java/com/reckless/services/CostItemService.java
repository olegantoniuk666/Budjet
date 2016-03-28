package com.reckless.services;

import java.util.List;

import com.reckless.businessobject.CostItem;

public interface CostItemService {

	public void addCostItem(CostItem item);

	public CostItem getCostItem(String category);
	
	public List<CostItem> getAllCostItems();
	
	public void removeByCategory(String category);
	
	

}
