package com.tommystore.service;

import com.tommystore.domain.Cart;
import com.tommystore.domain.Order;
import com.tommystore.exceptions.InsufficientStockException;

public interface OrderService {

	public 	void checkOut(Order order, Cart cart) throws InsufficientStockException;
	
}
