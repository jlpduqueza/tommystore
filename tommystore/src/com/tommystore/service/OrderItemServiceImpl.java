package com.tommystore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.domain.Cart;
import com.tommystore.domain.CartItem;
import com.tommystore.domain.OrderItem;
import com.tommystore.domain.Product;
import com.tommystore.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	@Transactional
	public OrderItem findOrderItemById(Integer id) {
		return orderItemRepository.findOrderItemById(id);
	}

	@Override
	@Transactional
	public OrderItem saveOrderItem(OrderItem orderItem) {
		return orderItemRepository.saveOrderItem(orderItem);
	}

	@Override
	@Transactional
	public List<OrderItem> getOrderItemList() {
		return orderItemRepository.getOrderItemList();
	}

	@Override
	@Transactional
	public List<OrderItem> generateOrderItemListByCart(Cart cart) {
		
		Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
		List<OrderItem> orderItemList = new ArrayList<>();
		
		for(Map.Entry<Integer, CartItem> map: cartItemMap.entrySet()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(map.getValue().getProduct().getPrice());
			orderItem.setProduct(map.getValue().getProduct());
			orderItem.setQuantity(map.getValue().getQuantity());
			orderItemList.add(orderItem);
		}
		
		
		return orderItemList;
	}

	@Override
	public List<OrderItem> findOrderItemsByProductId(Integer id) {
		return orderItemRepository.findOrderItemsByProductId(id);
	}

	@Override
	@Transactional
	public List<Product> getPopularProducts() {
		return orderItemRepository.getPopularProducts();
	}

}
