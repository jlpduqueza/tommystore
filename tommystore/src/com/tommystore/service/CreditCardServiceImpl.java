package com.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.domain.CreditCard;
import com.tommystore.repository.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Override
	@Transactional
	public CreditCard findCreditCardById(Integer id) {
		return creditCardRepository.findCreditCardById(id);
	}

	@Override
	@Transactional
	public CreditCard saveCreditCard(CreditCard creditCard) {
		return creditCardRepository.saveCreditCard(creditCard);
	}

	@Override
	@Transactional
	public List<CreditCard> getCreditCardList() {
		return creditCardRepository.getCreditCardList();
	}

	@Override
	@Transactional
	public void deleteCreditCardById(Integer id) {
		creditCardRepository.deleteCreditCardById(id);
	}

}
