package com.tommystore.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.OrderItem;
import com.tommystore.domain.Product;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public OrderItem findOrderItemById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	//note use this for saving order item
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

	@Override
	public List<OrderItem> findOrderItemsByProductId(Integer id) {
		TypedQuery<OrderItem> query =  em.createQuery("From OrderItem where product.id = :id order by order.purchaseDate DESC", OrderItem.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<Product> getPopularProducts() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date oneMonthInterval = cal.getTime();
		
		TypedQuery<Product> query =  em.createQuery("select product From OrderItem WHERE order.purchaseDate >= :oneMonthInterval group by product_id order by sum(quantity) DESC", Product.class);
		query.setParameter("oneMonthInterval", oneMonthInterval);
		query.setMaxResults(12);
		return query.getResultList();
	}

}
