package com.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.StockHistory;
import com.tommystore.domain.User;
import com.tommystore.repository.InventoryRepository;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Override
	@Transactional
	public InventoryItem findInventoryItemById(Integer id) {
		return inventoryRepository.findInventoryItemById(id);
	}

	@Override
	@Transactional
	public InventoryItem saveInventoryItem(InventoryItem inventoryItem, User user) {
		return inventoryRepository.saveInventoryItem(inventoryItem, user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<InventoryItem> getInventoryItemList() {
		return inventoryRepository.getInventoryItemList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<InventoryItem> findInventoryItemListByStock(int stock) {
		return inventoryRepository.findInventoryItemListByStock(stock);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StockHistory> getStockHistoryList() {
		return inventoryRepository.getStockHistoryList();
	}

	@Override
	public boolean isValidQuantity(String quantity) {
		try {
			Integer.parseInt(quantity);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isQuantitySufficient(int quantity, Integer id) {
		
		if(inventoryRepository.findInventoryItemById(id).getQuantity() >= quantity) {
			return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public InventoryItem addStock(InventoryItem inventoryItem, User user) {
        InventoryItem inventoryItemToMerge = findInventoryItemById(inventoryItem.getId());
        inventoryItemToMerge.setQuantity(inventoryItem.getQuantity());
        
		return saveInventoryItem(inventoryItemToMerge, user);
	}

}
