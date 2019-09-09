package com.tommystore.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tommystore.domain.Category;
import com.tommystore.service.CategoryService;
import com.tommystore.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
@PropertySource("/WEB-INF/properties")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Value("${invalid.categoryNameUsed}")
	String nameUsedErrorMessage;
	
	@Value("${invalid.categoryDeletion}")
	String deletionErrorMessage;
	
    @RequestMapping(value = "/category-list-view", method = RequestMethod.GET)
    public String categoryView(Model model) {
    	model.addAttribute("categoryList", categoryService.getCategoryList());
		return "admin-dashboard-categorylist";
    }
    
    @RequestMapping(value = "/add-category-view", method = RequestMethod.GET)
    public String addCategoryView(Model model) {
    	model.addAttribute("category", new Category());
		return "admin-dashboard-add-category";
    }
    
    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "admin-dashboard-add-category";
        }	
        
        if(categoryService.isCategoryExistByName(category.getName())) {
        	model.addAttribute("message", nameUsedErrorMessage);
            return "admin-dashboard-add-category";
        }
        
        categoryService.saveCategory(category);
		return "redirect:dashboard";
    }
    
    @RequestMapping(value = "/edit-category-view", method = RequestMethod.GET)
    public String editCategoryView(Model model, @RequestParam("id") Integer id) {
    	Category category = categoryService.findCategoryById(id);
    	model.addAttribute("category", category);
		return "admin-dashboard-edit-category";
    }
    
    @RequestMapping(value = "/edit-category", method = RequestMethod.POST)
    public String editCategory(@Valid Category categoryToEdit, BindingResult result, Model model) {
    	
        if (result.hasErrors()) {
        	Category category = categoryService.findCategoryById(categoryToEdit.getId());
        	model.addAttribute("category", category);
        	return "admin-dashboard-edit-category";
        }
        
        if(!categoryService.isValidToEditByIdAndName(categoryToEdit.getId(), categoryToEdit.getName())) {
        	Category category = categoryService.findCategoryById(categoryToEdit.getId());
        	model.addAttribute("message", nameUsedErrorMessage);
        	model.addAttribute("category", category);
        	return "admin-dashboard-edit-category";
        }
        
        Category category = categoryService.findCategoryById(categoryToEdit.getId());
        category.setName(categoryToEdit.getName());
        categoryService.saveCategory(category);
        
		return "redirect:category-list-view";
    }
    
    @RequestMapping(value = "/delete-category", method = RequestMethod.GET)
    public String deleteCategory(Model model, @RequestParam("id") Integer id) {

		if(productService.findProductByCategory(id).size() != 0) {
	    	model.addAttribute("categoryList", categoryService.getCategoryList());
	    	model.addAttribute("message", deletionErrorMessage);
			return "admin-dashboard-categorylist";
		}
    	categoryService.deleteCategory(id);
    	
		return "redirect:category-list-view";
    }
}
