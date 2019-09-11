package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.OrderItem;
import com.tommystore.domain.Product;

public interface OrderItemRepository {

	public OrderItem findOrderItemById(Integer id);
	public OrderItem saveOrderItem(OrderItem orderItem);
	public List<OrderItem> getOrderItemList();
	public List<OrderItem> findOrderItemsByProductId(Integer id);
	public List<Product> getPopularProducts();
	
}
