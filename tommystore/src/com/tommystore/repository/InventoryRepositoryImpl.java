package com.tommystore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.StockHistory;
import com.tommystore.domain.User;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public InventoryItem findInventoryItemById(Integer id) {
		return em.find(InventoryItem.class, id);
	}

	@Override
	public InventoryItem saveInventoryItem(InventoryItem inventoryItem, User user) {
        if (inventoryItem.getId() == null) {
	        em.persist(inventoryItem);
        } else {
            inventoryItem = em.merge(inventoryItem);
            StockHistory stockHistory = new StockHistory();
            stockHistory.setInventoryItem(inventoryItem);
            stockHistory.setUser(user);
            stockHistory.setUpdatedQuantity(inventoryItem.getQuantity());
            em.persist(stockHistory);
        }
        return inventoryItem;
	}

	@Override
	public List<InventoryItem> getInventoryItemList() {
		TypedQuery<InventoryItem> query =  em.createQuery("From InventoryItem", InventoryItem.class);
		return query.getResultList();
	}

	@Override
	public List<InventoryItem> findInventoryItemListByStock(int stock) {
		TypedQuery<InventoryItem> query =  em.createQuery("From InventoryItem where quantity <= :nStock", InventoryItem.class);
		query.setParameter("nStock", stock);
		return query.getResultList();
	}

	@Override
	public InventoryItem findInventoryItemByProductId(Integer id) {
		TypedQuery<InventoryItem> query =  em.createQuery("From InventoryItem where product.id = :id", InventoryItem.class);
		query.setParameter("id", id);
		System.out.println(query.getSingleResult().getProduct().getName());
		return query.getSingleResult();
	}

	@Override
	public List<StockHistory> getStockHistoryList() {
		TypedQuery<StockHistory> query =  em.createQuery("From StockHistory", StockHistory.class);
		return query.getResultList();
	}

}
