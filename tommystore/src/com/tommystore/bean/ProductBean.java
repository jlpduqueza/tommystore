package com.tommystore.bean;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.tommystore.domain.Category;

public class ProductBean {

	@NotBlank
	private String name;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private Category category;
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public BigDecimal getPrice() {
		return price;
	}

	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	
}
