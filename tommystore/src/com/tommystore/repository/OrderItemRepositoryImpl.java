package com.tommystore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.OrderItem;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public OrderItem findOrderItemById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem saveOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemList() {
		// TODO Auto-generated method stub
		return null;
	}

}
