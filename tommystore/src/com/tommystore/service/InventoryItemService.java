package com.tommystore.service;

import java.util.List;

import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.StockHistory;
import com.tommystore.domain.User;

public interface InventoryItemService {
	
	public InventoryItem findInventoryItemById(Integer id);
	public InventoryItem saveInventoryItem(InventoryItem inventoryItem, User user);
	public boolean isValidQuantity(String quantity);
	public boolean isQuantitySufficient(int quantity, Integer id);
	public List<InventoryItem> getInventoryItemList();
	public List<StockHistory> getStockHistoryList();
	public List<InventoryItem> findInventoryItemListByStock(int stock);
}
