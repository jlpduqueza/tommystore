package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.StockHistory;
import com.tommystore.domain.User;

public interface InventoryRepository {

	public InventoryItem findInventoryItemById(Integer id);
	public InventoryItem findInventoryItemByProductId(Integer id);
	public InventoryItem saveInventoryItem(InventoryItem inventoryItem, User user);
	public List<InventoryItem> getInventoryItemList();
	public List<StockHistory> getStockHistoryList();
	public List<InventoryItem> findInventoryItemListByStock(int stock);
	
}
