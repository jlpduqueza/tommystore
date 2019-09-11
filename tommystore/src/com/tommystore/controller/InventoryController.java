package com.tommystore.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.User;
import com.tommystore.service.InventoryItemService;

@Controller
@RequestMapping(value = "/admin")
public class InventoryController {

	@Autowired
	private InventoryItemService inventoryItemService;
	
	@Value("${inventory.nstock}")
	private int nStock;
	
	@Value("${invalid.invalidQuantity}")
	private String invalidQuantityMessage;
	
    @RequestMapping(value = "/inventoryitem-list-view", method = RequestMethod.GET)
    public String inventoryItemView(Model model) {
    	model.addAttribute("inventoryItemList", inventoryItemService.getInventoryItemList());
		return "admin-dashboard-inventoryitemlist";
    }
    
    @RequestMapping(value = "/stock-history-view", method = RequestMethod.GET)
    public String stockHistoryView(Model model) {
    	model.addAttribute("stockHistoryList", inventoryItemService.getStockHistoryList());
		return "admin-dashboard-stockhistorylist";
    }
    
    @RequestMapping(value = "/add-stock-view/{id}", method = RequestMethod.GET)
    public String editInventoryItemView(Model model, @PathVariable("id") Integer id) {
    	InventoryItem inventoryItem = inventoryItemService.findInventoryItemById(id);
    	model.addAttribute("inventoryItem", inventoryItem);
		return "admin-dashboard-add-stock";
    }
    
    @RequestMapping(value = "/add-stock", method = RequestMethod.POST)
    public String inventoryItem(@Valid InventoryItem inventoryItem, BindingResult result, Model model, HttpSession session, RedirectAttributes attributes) {
    	
        if (result.hasErrors()) {
        	attributes.addFlashAttribute("message", invalidQuantityMessage);
    		return "redirect:add-stock-view/"+inventoryItem.getId();
        }	
        
        inventoryItemService.addStock(inventoryItem, (User) session.getAttribute("user"));
		return "redirect:dashboard";
    }
}
