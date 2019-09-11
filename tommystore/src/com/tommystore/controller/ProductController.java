package com.tommystore.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommystore.bean.ProductBean;
import com.tommystore.domain.Category;
import com.tommystore.domain.Product;
import com.tommystore.service.CategoryService;
import com.tommystore.service.OrderItemService;
import com.tommystore.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
@PropertySource("/WEB-INF/properties")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Value("${inventory.nstock}")
	private int nStock;
	
	@Value("${invalid.productNameUsed}")
	String productNameUsedMessage;
	
	@Value("${invalid.invalidPrice}")
	String invalidPriceMessage;
	
    @RequestMapping(value = "/product-list-view", method = RequestMethod.GET)
    public String productView(Model model) {
    	model.addAttribute("productList", productService.getProductList());
		return "admin-dashboard-productlist";
    }
    
    @RequestMapping(value = "/add-product-view", method = RequestMethod.GET)
    public String addProductView(Model model) {
    	model.addAttribute("productBean", new ProductBean());
    	model.addAttribute("categoryMap",categoryService.getCategoryMap());
		return "admin-dashboard-add-product";
    }
    
    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public String addProduct(@Valid ProductBean productBean, BindingResult result, Model model, HttpSession session) {
    	
        if (result.hasErrors()) {
        	model.addAttribute("categoryMap",categoryService.getCategoryMap());
            return "admin-dashboard-add-product";
        }	
        
        if(productService.isProductExistByNameAndCategoryId(productBean.getName(), productBean.getCategory().getId())) {
        	model.addAttribute("message", productNameUsedMessage);
        	model.addAttribute("categoryMap",categoryService.getCategoryMap());
            return "admin-dashboard-add-product";
        }
        
        if(!productService.isPriceValid(productBean.getPrice())) {
        	model.addAttribute("message", invalidPriceMessage);
        	model.addAttribute("categoryMap",categoryService.getCategoryMap());
            return "admin-dashboard-add-product";
        }
        
        productService.saveProductByBean(productBean);
		return "redirect:dashboard";
    }
    
    @RequestMapping(value = "/edit-product-view", method = RequestMethod.GET)
    public String editProductView(Model model, @RequestParam("id") Integer id) {
    	Product product = productService.findProductById(id);
    	model.addAttribute("product", product);
    	model.addAttribute("categoryMap",categoryService.getCategoryMap());
		return "admin-dashboard-edit-product";
    }
    
    //note convert this to productBean
    @RequestMapping(value = "/edit-product", method = RequestMethod.POST)
    public String editCategory(@Valid Product productToEdit, BindingResult result, Model model) {
    	
    	if(productToEdit.getPrice() == null) {
        	Product product = productService.findProductById(productToEdit.getId());
        	model.addAttribute("product", product);
        	model.addAttribute("categoryMap",categoryService.getCategoryMap());
        	model.addAttribute("message", invalidPriceMessage);
    		return "admin-dashboard-edit-product";
    	}
    	
        if(productService.isNameValid(productToEdit.getName(), productToEdit.getId())) {
        	model.addAttribute("message", productNameUsedMessage);
        	model.addAttribute("categoryMap",categoryService.getCategoryMap());
            return "admin-dashboard-edit-product";
        }
    	
        if (result.hasErrors()) {
        	Product product = productService.findProductById(productToEdit.getId());
        	model.addAttribute("product", product);
        	model.addAttribute("categoryMap",categoryService.getCategoryMap());
    		return "admin-dashboard-edit-product";
        }	
        
        Product product = productService.findProductById(productToEdit.getId());
        product.setCategory(productToEdit.getCategory());
        product.setName(productToEdit.getName());
        product.setPrice(productToEdit.getPrice());
        productService.saveProduct(product);
		return "redirect:product-list-view";
    }
    
    @RequestMapping(value = "/delete-product", method = RequestMethod.GET)
    public String deleteProduct(Model model, @RequestParam("id") Integer id) {
    	productService.deleteProductById(id);
		return "redirect:product-list-view";
    }
    
    @RequestMapping(value = "/product-orders-view", method = RequestMethod.GET)
    public String showOrderPerProduct(Model model, @RequestParam("id") Integer id) {
    	model.addAttribute("orderListPerProduct", orderItemService.findOrderItemsByProductId(id));
		return "admin-dashboard-product-orders-view";
    }
    
    @ExceptionHandler(NumberFormatException.class)
    public String UserNotFoundHandler(Exception ex, Model model, RedirectAttributes attributes) {
        
    	attributes.addFlashAttribute("message", invalidPriceMessage);
        return "redirect:login";
    }
}
