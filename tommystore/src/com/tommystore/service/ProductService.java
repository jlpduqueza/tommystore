package com.tommystore.service;

import java.util.List;

import com.tommystore.domain.Product;

public interface ProductService {

	public Product findProductById(Integer id);
	public Product saveProduct(Product product);
	public List<Product> getProductList();
	public boolean isProductExistByNameAndCategoryId(String name, Integer id);
	public boolean isPriceValid(String price);
	public void deleteProductById(Integer id);
	public List<Product> findProductByCategory(Integer id);
	
}
