package com.tommystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.domain.Order;
import com.tommystore.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	@Transactional
	public void checkOut(Order order) {
		orderRepository.checkOut(order);
	}
	
}
