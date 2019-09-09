package com.tommystore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.ShippingAddress;

@Repository
public class ShippingAddressRepositoryImpl implements ShippingAddressRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public ShippingAddress findShippingAddressById(Integer id) {
		return em.find(ShippingAddress.class, id);
	}

	@Override
	public ShippingAddress saveShippingAddress(ShippingAddress shippingAddress) {
        if (shippingAddress.getId() == null) {
	        em.persist(shippingAddress);
        } else {
            shippingAddress = em.merge(shippingAddress);
        }
        return shippingAddress;
	}

	@Override
	public List<ShippingAddress> getShippingAddress() {
		TypedQuery<ShippingAddress> query =  em.createQuery("From ShippingAddress", ShippingAddress.class);
		return query.getResultList();
	}

	@Override
	public void deleteShippingAddressById(Integer id) {
		em.remove(em.find(ShippingAddress.class, id));
	}

}
