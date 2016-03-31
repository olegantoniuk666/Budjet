package com.reckless.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ModelAndView costItemViewer() {
			ModelAndView costItemViewer = new ModelAndView();
			List<CostItem> allCostItems = costItemService.getAllCostItems();
			costItemViewer.addObject("allCostItems", allCostItems);
			costItemViewer.addObject("costItemService", costItemService);
			costItemViewer.setViewName("costItemViewer");
		return costItemViewer;

	}

	@RequestMapping(value = "/createCostItem", method = RequestMethod.POST)
	public String createCostItem(
			@RequestParam("category") String category,
			@RequestParam("plan") int plan) {
		if (category != null && plan!=0) {
			CostItem item = new CostItem(category,plan);
			costItemService.addCostItem(item);
		}
		return "costItemViewer";
		
	}

	
}
