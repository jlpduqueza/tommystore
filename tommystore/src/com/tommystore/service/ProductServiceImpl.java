package com.tommystore.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.bean.ProductBean;
import com.tommystore.domain.Category;
import com.tommystore.domain.Product;
import com.tommystore.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	@Transactional
	public Product findProductById(Integer id) {
		return productRepository.findProductById(id);
	}

	@Override
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.saveProduct(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductList() {
		return productRepository.getProductList();
	}

	@Override
	@Transactional
	public void deleteProductById(Integer id) {
		productRepository.deleteProductById(id);
	}

	@Override
	@Transactional
	public Boolean isProductExistByNameAndCategoryId(String name, Integer id) {
		return productRepository.isProductExistByNameAndCategoryId(name,id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> findProductByCategory(Integer id) {
		return productRepository.findProductByCategory(id);
	}

	@Override
	public Boolean isPriceValid(String price) {
		try {
			new BigDecimal(price);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public Boolean isNameValid(String name, Integer id) {
		return productRepository.isNameValid(name, id);
	}

	@Override
	public List<Product> getPopularProducts() {
		return productRepository.getPopularProducts();
	}

	@Override
	public List<Product> searchProduct(String keyword) {
		return productRepository.searchProduct(keyword);
	}

	@Override
	public Product saveProductByBean(ProductBean productBean) {
        Product product = new Product();
        Category category = categoryService.findCategoryById(productBean.getCategory().getId());
        product.setCategory(category);
        product.setName(productBean.getName());
        product.setPrice(new BigDecimal(productBean.getPrice()));
        
		return saveProduct(product);
	}

}
