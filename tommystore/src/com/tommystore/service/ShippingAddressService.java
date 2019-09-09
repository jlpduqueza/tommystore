package com.tommystore.service;

import java.util.List;

import com.tommystore.constant.Country;
import com.tommystore.domain.ShippingAddress;

public interface ShippingAddressService {

	public ShippingAddress findShippingAddressById(Integer id);
	public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress);
	public List<Country> getCountryList();
	public List<ShippingAddress> getShippingAddressList();
	public void deleteShippingAddressById(Integer id);
	
}
