package com.tommystore.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tommystore.domain.Cart;
import com.tommystore.domain.CartItem;
import com.tommystore.domain.InventoryItem;
import com.tommystore.domain.Order;
import com.tommystore.domain.OrderItem;
import com.tommystore.exceptions.InsufficientStockException;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public void checkOut(Order order, Cart cart) throws InsufficientStockException {
		
		Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
		List<OrderItem> orderItemList = new ArrayList<>();
		
		for(Map.Entry<Integer, CartItem> map: cartItemMap.entrySet()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(map.getValue().getProduct().getPrice());
			orderItem.setProduct(map.getValue().getProduct());
			orderItem.setQuantity(map.getValue().getQuantity());
			orderItemList.add(orderItem);
		}
		
		em.persist(order);
		
		Order newOrder = em.merge(order);
		
		for(OrderItem orderItem : orderItemList) {
			orderItem.setOrder(newOrder);
			em.persist(orderItem);
			
			InventoryItem inventoryItem = inventoryRepository.findInventoryItemByProductId(orderItem.getProduct().getId());

			if(inventoryItem.getQuantity() < orderItem.getQuantity()) {
				throw new InsufficientStockException();
			}
			
			inventoryItem.setQuantity(inventoryItem.getQuantity()-orderItem.getQuantity());
			em.merge(inventoryItem);
		}
	}

	@Override
	public Order findOrderById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}
