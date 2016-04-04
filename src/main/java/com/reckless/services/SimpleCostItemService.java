package com.reckless.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.reckless.businessobject.CostItem;
import com.reckless.businessobject.Transaction;

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
		List<CostItem> costItems = new ArrayList<CostItem>(100); 
				costItems = costItemRepository.findAll();
				Iterator<CostItem>costItemIterator = costItems.iterator();
				while(costItemIterator.hasNext()){
					CostItem item = costItemIterator.next();
					item.setPlan(getPlanByCategory(item.getCategory()));
					item.setFakt(getFactByCategory(item.getCategory()));
					item.setBalance(getBalanceByCategory(item.getCategory()));
				}
		return costItems;
	}

	public void removeByCategory(String category) {
		List<CostItem> costItems = costItemRepository.findAll();
		Iterator<CostItem> iter = costItems.iterator();
		while (iter.hasNext()) {
			CostItem item = iter.next();
			if (item.getCategory().equals(category)) {
				iter.remove();
			}
		}

	}

	public int getPercentByAllCostItems() {
		List<CostItem> allCostItems = new ArrayList<CostItem>();
		allCostItems = costItemRepository.findAll();
		Iterator<CostItem> iter = allCostItems.iterator();
		int planByAllItems = 0;
		while (iter.hasNext()) {
			CostItem item = iter.next();
			planByAllItems = planByAllItems + item.getPlanPercent();
		}
		return planByAllItems;
	}

	public double getPlanByCategory(String category) {
		CostItem item = getCostItem(category);
		int plan = (transactionService.getIncomeTransactionsQuantity())
				* (item.getPlanPercent()) / 100;
		return plan;
	}

	public double getFactByCategory(String category) {
		List<Transaction> sortedByCostItemCategory = new ArrayList<Transaction>();
		sortedByCostItemCategory = transactionService
				.getTransactionsByCategory(category);
		int fakt = 0;
		Iterator<Transaction> iter = sortedByCostItemCategory.iterator();
		while (iter.hasNext()) {
			Transaction transaction = iter.next();
			fakt = fakt + transaction.getQuantity();

		}
		return fakt;

	}

	public double getBalanceByCategory(String category) {
		double balance = getPlanByCategory(category)-getFactByCategory(category);
		return balance;
	}
}
