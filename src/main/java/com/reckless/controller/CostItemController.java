package com.reckless.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.reckless.businessobject.CostItem;
import com.reckless.services.CostItemService;

@Controller
@EnableAutoConfiguration
public class CostItemController {

	@Autowired
	private CostItemService costItemService;

	@RequestMapping(value = "/costItemViewer", method = RequestMethod.GET)
	public String costItemViewer() {
		return "costItemViewer";

	}
	@RequestMapping(value = "/costItemCreator", method = RequestMethod.GET)
	public String costItemCreator() {
		return "costItemCreator";

	}

	@RequestMapping(value = "/createCostItem", method = RequestMethod.POST)
	public ModelAndView createCostItem(
			@RequestParam("category") String category,
			@RequestParam("plan") int plan) {
		ModelAndView model = new ModelAndView();
		model.setViewName("costItemViewer");
		if (category != null && plan!=0) {
			CostItem item = new CostItem();
			item.setCategory(category);
			item.setPlan(plan);
			costItemService.addCostItem(item);
			model.addObject("costItem", item);
		} else {
			model.addObject("message", "input correct data");
		}
		return model;
	}

	@RequestMapping(value = "showAllCostItems", method = RequestMethod.GET)
	public ModelAndView showAllCostItems() {
		ModelAndView model = new ModelAndView();
		List<CostItem> allCostItems = costItemService.getAllCostItems();
		model.addObject("allCostItems", allCostItems);
		model.setViewName("AllCostItems");
		return model;
	}
}
