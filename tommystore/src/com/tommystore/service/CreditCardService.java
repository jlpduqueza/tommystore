package com.tommystore.service;

import java.util.List;

import com.tommystore.domain.CreditCard;

public interface CreditCardService {

	public CreditCard findCreditCardById(Integer id);
	public CreditCard saveCreditCard(CreditCard creditCard);
	public void deleteCreditCardById(Integer id);
	public List<CreditCard> getCreditCardList();
	
}
