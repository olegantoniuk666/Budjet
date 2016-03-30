package com.reckless.businessobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import com.reckless.services.TransactionService;

public class CostItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TransactionService transactionService;

	public CostItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CostItem(String category, int planPercent) {
		super();
		this.category = category;
		this.planPercent = planPercent;
		
	}

	@Override
	public String toString() {
		return "CostItem [ category=" + category + ", plan=" + planPercent
				+ "id=" + id + "]";
	}

	@Id
	private String id;
	private String category;
	private int planPercent;

//	private int fakt;
//	private int plan;
//	private int balance;

	public String getCategory() {
		return category;
	}

	public void setCategory(String name) {
		this.category = name;
	}


	public int getPlanPercent() {
		return planPercent;
	}

	public void setPlanPercent(int planPercent) {
		this.planPercent = planPercent;
	}

	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CostItem other = (CostItem) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (planPercent != other.planPercent)
			return false;
		if (transactionService == null) {
			if (other.transactionService != null)
				return false;
		} else if (!transactionService.equals(other.transactionService))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, planPercent);
	}

	
}
