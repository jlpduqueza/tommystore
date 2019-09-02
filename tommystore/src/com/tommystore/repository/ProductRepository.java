package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.Product;

public interface ProductRepository {

	public Product findProductById(Integer id);
	public Product saveProduct(Product product);
	public List<Product> findProductByCategory(Integer id);
	public List<Product> getProductList();
	public Boolean isProductExistByNameAndCategoryId(String name, Integer id);
//	public Boolean isValidPrice
	public void generateProductId(Product product);
	public void deleteProductById(Integer id);
	
}
