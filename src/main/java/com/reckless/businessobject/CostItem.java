package com.reckless.businessobject;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;

public class CostItem implements Serializable {
	@Override
	public String toString() {
		return "CostItem [ category=" + category + ", plan=" + plan + "id="+id +"]";
	}

	@Id
	
	private String id;
	private String category;
	private int plan;

	public String getCategory() {
		return category;
	}

	public void setCategory(String name) {
		this.category = name;
	}

	public int getPlan() {
		return plan;
	}

	public void setPlan(int plan) {
		this.plan = plan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, category, plan);

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
		if (plan != other.plan)
			return false;
		return true;
	}

}
