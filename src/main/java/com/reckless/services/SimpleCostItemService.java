package com.reckless.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reckless.businessobject.CostItem;

public class SimpleCostItemService implements CostItemService {

	@Autowired
	private CostItemRepository costItemRepository;

	public void addCostItem(CostItem item) {
		costItemRepository.save(item);
	}

	public CostItem getCostItem(String category) {
		List<CostItem> costItems = costItemRepository.findAll();
		for (CostItem item : costItems) {
			if (item.getCategory().equals(category)) {
				return item;
			}
		}
		return null;
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

}
