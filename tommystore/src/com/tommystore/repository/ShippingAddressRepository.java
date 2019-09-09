package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.ShippingAddress;

public interface ShippingAddressRepository {

	public ShippingAddress findShippingAddressById(Integer id);
	public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress);
	public void deleteShippingAddressById(Integer id);
	public List<ShippingAddress> getShippingAddress();
	
}

