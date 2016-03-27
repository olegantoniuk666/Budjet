package com.reckless.businessobject;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;

import com.reckless.config.DateUtils;

public class Transaction {

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(String category, int quantity, Date date, Boolean isIncome) {
		super();
		this.category = category;
		this.quantity = quantity;
		this.date = date;
		this.isIncome = isIncome;
	}

	@Id
	private String id;
	private String category;
	private int quantity;
	private Date date;
	private Boolean isIncome = false;

	public Boolean getIsIncome() {
		return isIncome;
	}

	public void setIsIncome(Boolean isIncome) {
		this.isIncome = isIncome;
	}

	public LocalDate getLocalDate() {
		return DateUtils.asLocalDate(this.date);

	}

	public void setLocalDate(LocalDate localDate) {
		this.date = DateUtils.asDate(localDate);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, date, quantity, isIncome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
