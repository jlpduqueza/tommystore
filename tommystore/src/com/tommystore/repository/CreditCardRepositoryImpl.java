package com.tommystore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommystore.domain.CreditCard;

@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public CreditCard findCreditCardById(Integer id) {
		return em.find(CreditCard.class, id);
	}

	@Override
	public CreditCard saveCreditCard(CreditCard creditCard) {
        if (creditCard.getId() == null) {
	        em.persist(creditCard);
        } else {
            creditCard = em.merge(creditCard);
        }
        return creditCard;
	}

	@Override
	public List<CreditCard> getCreditCardList() {
		TypedQuery<CreditCard> query =  em.createQuery("From CreditCard", CreditCard.class);
		return query.getResultList();
	}

	@Override
	public void deleteCreditCardById(Integer id) {
		em.remove(em.find(CreditCard.class, id));
	}

}