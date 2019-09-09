package com.tommystore.service;

import java.util.List;

import com.tommystore.domain.Cart;
import com.tommystore.domain.OrderItem;

public interface OrderItemService {

	public OrderItem findOrderItemById(Integer id);
	public OrderItem saveOrderItem(OrderItem orderItem);
	public List<OrderItem> getOrderItemList();
	public List<OrderItem> generateOrderItemListByCart(Cart cart);
	
}