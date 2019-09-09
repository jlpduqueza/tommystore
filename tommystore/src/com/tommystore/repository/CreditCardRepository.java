package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.CreditCard;

public interface CreditCardRepository {

	public CreditCard findCreditCardById(Integer id);
	public CreditCard saveCreditCard(CreditCard creditCard);
	public void deleteCreditCardById(Integer id);
	public List<CreditCard> getCreditCardList();
	
}
