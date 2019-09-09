package com.tommystore.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CreditCardBean {

	@NotNull
	@Size(min = 10, max = 10)
	@NotBlank
	private String cardNumber;
	
	@NotNull
	@Size(min = 5, max = 5)
	@NotBlank
	private String securityCode;
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
}
