package com.tommystore.service;

import com.tommystore.domain.Cart;
import com.tommystore.domain.CartItem;

public interface CartItemService {

	public Cart addToCart(Cart cart, CartItem cartItem);
	public void deleteCart(Cart cart);
}