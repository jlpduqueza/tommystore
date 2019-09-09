package com.tommystore.repository;

import com.tommystore.domain.Order;

public interface OrderRepository {

	public void checkOut(Order order);
	public Order findOrderById(Integer id);
	public Order saveOrder(Order order);
	
}
