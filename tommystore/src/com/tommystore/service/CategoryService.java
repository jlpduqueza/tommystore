package com.tommystore.service;

import java.util.List;
import java.util.Map;

import com.tommystore.domain.Category;

public interface CategoryService {
	
	public Category findCategoryById(Integer id);
	public Category saveCategory(Category category);
	public List<Category> getCategoryList();
	public Boolean isCategoryExistByName(String name);
	public Map<Integer, String> getCategoryMap();
	public void deleteCategory(Integer id);
	public Boolean isValidToEditByIdAndName(Integer id, String name);
	
}
