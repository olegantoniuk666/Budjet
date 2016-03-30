package com.reckless.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reckless.businessobject.CostItem;

public class SimpleCostItemService implements CostItemService {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private CostItemRepository costItemRepository;

	public void addCostItem(CostItem item) {
		costItemRepository.save(item);
	}

	public CostItem getCostItem(String category) {
		List<CostItem> costItems = new ArrayList<CostItem>();
				costItems = costItemRepository.getByCategory(category);
		return costItems.get(0);
	}

	public List<CostItem> getAllCostItems() {
		List<CostItem> costItems = costItemRepository.findAll();
		return costItems;
	}

	public void removeByCategory(String category) {
		List<CostItem> costItems = costItemRepository.findAll();
		Iterator<CostItem> iter = costItems.iterator();
		while (iter.hasNext()){
			CostItem item = iter.next();
			if (item.getCategory().equals(category)){
				iter.remove();
			}
		}
		
	}

	@Override
	public int getPercentByAllCostItems() {
		List <CostItem> allCostItems = new ArrayList<CostItem>();
		allCostItems = costItemRepository.findAll();
		Iterator<CostItem> iter = allCostItems.iterator();
		int  planByAllItems =0;
		while(iter.hasNext()){
			CostItem item = iter.next();
			planByAllItems= planByAllItems+item.getPlan();
		}
		return planByAllItems;
	}
	
}
