package com.tommystore.service;

import java.util.List;

import com.tommystore.domain.Product;

public interface ProductService {

	public Product findProductById(Integer id);
	public Product saveProduct(Product product);
	public List<Product> getProductList();
	public List<Product> getPopularProducts();
	public List<Product> searchProduct(String keyword);
	public List<Product> findProductByCategory(Integer id);
	public Boolean isProductExistByNameAndCategoryId(String name, Integer id);
	public Boolean isPriceValid(String price);
	public Boolean isNameValid(String name, Integer id);
	public void deleteProductById(Integer id);
	
}
