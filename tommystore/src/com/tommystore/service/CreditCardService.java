package com.tommystore.service;

import java.util.List;

import com.tommystore.bean.CreditCardBean;
import com.tommystore.domain.CreditCard;
import com.tommystore.domain.User;

public interface CreditCardService {

	public CreditCard findCreditCardById(Integer id);
	public CreditCard saveCreditCard(CreditCard creditCard);
	public void deleteCreditCardById(Integer id);
	public List<CreditCard> getCreditCardList();
	public CreditCard saveCreditCardByBean(CreditCardBean creditCardBean, User user);
	
}
