package com.tommystore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.domain.Cart;
import com.tommystore.domain.CartItem;
import com.tommystore.domain.OrderItem;
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
		
		List<CartItem> cartItemList = new ArrayList<>(cart.getCartItemMap().values());
		List<OrderItem> orderItemList = new ArrayList<>();
		
		for(CartItem cartItem : cartItemList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(cartItem.getProduct().getPrice());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItemList.add(orderItem);
		}
		
		return orderItemList;
	}

}
