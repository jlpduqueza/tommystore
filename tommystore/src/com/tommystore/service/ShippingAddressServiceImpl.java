package com.tommystore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.constant.Country;
import com.tommystore.domain.ShippingAddress;
import com.tommystore.repository.ShippingAddressRepository;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService{

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Override
	@Transactional
	public ShippingAddress findShippingAddressById(Integer id) {
		return shippingAddressRepository.findShippingAddressById(id);
	}

	@Override
	@Transactional
	public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress) {
		return shippingAddressRepository.saveShippingAddress(shippingAddress);
	}

	@Override
	@Transactional
	public List<ShippingAddress> getShippingAddressList() {
		return shippingAddressRepository.getShippingAddress();
	}

	@Override
	@Transactional
	public void deleteShippingAddressById(Integer id) {
		shippingAddressRepository.deleteShippingAddressById(id);
	}

	@Override
	public List<Country> getCountryList() {
    	List<Country> countryList = new ArrayList<>();
    	
    	for (Country country : Country.values()) {
    		countryList.add(country);
    	}
    	return countryList;
	}

}
