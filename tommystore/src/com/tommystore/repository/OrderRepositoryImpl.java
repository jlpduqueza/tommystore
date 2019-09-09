package com.tommystore.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.Order;
import com.tommystore.domain.OrderItem;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void checkOut(Order order) {
		em.persist(order);
		
		for(OrderItem orderItem : order.getOrderItems()) {
			orderItem.setOrder(em.merge(order));
			em.persist(orderItem);
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