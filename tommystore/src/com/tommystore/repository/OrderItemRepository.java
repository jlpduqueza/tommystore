package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.OrderItem;

public interface OrderItemRepository {

	public OrderItem findOrderItemById(Integer id);
	public OrderItem saveOrderItem(OrderItem orderItem);
	public List<OrderItem> getOrderItemList();
	
}