package com.tommystore.repository;

import com.tommystore.domain.Cart;
import com.tommystore.domain.Order;
import com.tommystore.exceptions.InsufficientStockException;

public interface OrderRepository {

	public void checkOut(Order order, Cart cart) throws InsufficientStockException;
	public Order findOrderById(Integer id);
	public Order saveOrder(Order order);
	
	
}
