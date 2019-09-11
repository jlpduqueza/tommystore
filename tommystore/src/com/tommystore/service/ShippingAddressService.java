package com.tommystore.service;

import java.util.List;

import com.tommystore.bean.ShippingAddressBean;
import com.tommystore.constant.Country;
import com.tommystore.domain.ShippingAddress;
import com.tommystore.domain.User;

public interface ShippingAddressService {

	public ShippingAddress findShippingAddressById(Integer id);
	public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress);
	public List<Country> getCountryList();
	public List<ShippingAddress> getShippingAddressList();
	public void deleteShippingAddressById(Integer id);
	public ShippingAddress saveShippingAddressByBean(ShippingAddressBean shippingAddressBean, User user);
	
}

