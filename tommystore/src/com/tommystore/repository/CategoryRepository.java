package com.tommystore.repository;

import java.util.List;
import java.util.Map;

import com.tommystore.domain.Category;

public interface CategoryRepository {

	public Category findCategoryById(Integer id);
	public Category saveCategory(Category category);
	public List<Category> getCategoryList();
	public Boolean isCategoryExistByName(String name);
	public Map<Integer, String> getCategoryMap();
	public void deleteCategory(Integer id) ;
	public Boolean isValidCategoryName(Integer id, String name);
	
	
}
