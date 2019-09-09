package com.tommystore.domain;

import javax.persistence.ManyToOne;

public class CartItem {
	
	private Integer id;
	
	@ManyToOne(optional=false)
	private Product product;
	
	private int quantity;

	@ManyToOne(optional=false)
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}