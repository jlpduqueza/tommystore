package com.tommystore.repository;

import java.util.List;

import com.tommystore.domain.Product;

public interface ProductRepository {

	public Product findProductById(Integer id);
	public Product saveProduct(Product product);
	public List<Product> findProductByCategory(Integer id);
	public List<Product> getProductList();
	public List<Product> getPopularProducts();
	public List<Product> searchProduct(String keyword);
	public Boolean isProductExistByNameAndCategoryId(String name, Integer id);
	public Boolean isNameValid(String name, Integer id);
	public void generateProductId(Product product);
	public void deleteProductById(Integer id);
	
}
