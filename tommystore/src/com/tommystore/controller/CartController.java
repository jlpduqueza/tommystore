package com.tommystore.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommystore.domain.Cart;
import com.tommystore.domain.CartItem;
import com.tommystore.domain.Order;
import com.tommystore.domain.Product;
import com.tommystore.domain.User;
import com.tommystore.service.CartService;
import com.tommystore.service.CreditCardService;
import com.tommystore.service.InventoryItemService;
import com.tommystore.service.OrderItemService;
import com.tommystore.service.OrderService;
import com.tommystore.service.ProductService;
import com.tommystore.service.ShippingAddressService;

@Controller
@RequestMapping(value = "/user")
@PropertySource("/WEB-INF/properties")
public class CartController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private InventoryItemService inventoryItemService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private OrderService orderService;	

	@Autowired
	private OrderItemService orderItemService;	
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	@Value("${insufficient.quantity}")
	private String insufficientQuantityMessage;
	
	@Value("${invalid.zeroQuantity}")
	private String zeroQuantityMessage;
	
	@Value("${invalid.noCart}")
	private String noCartMessage;
    
    @RequestMapping(value = "/add-to-cart-view", method = RequestMethod.GET)
    public String addToCartView(Model model, @RequestParam("id") Integer id) {

    	model.addAttribute("product", productService.findProductById(id));
    	model.addAttribute("cartItem", new CartItem());
		return "user-addtocart";
    }
    
    @RequestMapping(value = "/add-to-cart", method = RequestMethod.POST)
    public String addToCart(@Valid CartItem cartItem, Model model, HttpSession session) {
    	Integer productId = cartItem.getProduct().getId();
    	Product product = productService.findProductById(cartItem.getProduct().getId());
    	
    	if(cartItem.getQuantity() == 0) {
        	model.addAttribute("product", product);
        	model.addAttribute("cartItem", new CartItem());
        	model.addAttribute("message", zeroQuantityMessage);
    		return "user-addtocart";
    	}
    	
    	if(!inventoryItemService.isQuantitySufficient(cartItem.getQuantity(), product.getInventoryItem().getId())) {
        	model.addAttribute("product", product);
        	model.addAttribute("cartItem", new CartItem());
        	model.addAttribute("message", insufficientQuantityMessage);
    		return "user-addtocart";
    	}
    	
    	if(session.getAttribute("cart") == null) {
        	Cart cart = new Cart();
        	
        	Map<Integer, CartItem> cartItemMap = new HashMap<>();
        	
        	cartItemMap.put(productId, cartItem);
        	cart.setCartItemMap(cartItemMap);
        	
        	session.setAttribute("cart", cart);
    		return "redirect:/";
    	}

    	Cart cart = (Cart) session.getAttribute("cart");
    	
    	if(!cart.getCartItemMap().containsKey(productId)) {
        	cart.getCartItemMap().put(productId, cartItem);
        	
        	session.setAttribute("cart", cart);
    		return "redirect:/";
    	}
    	
    	int quantity = cart.getCartItemMap().get(productId).getQuantity()+cartItem.getQuantity();
    	
    	if(!inventoryItemService.isQuantitySufficient(quantity, product.getInventoryItem().getId())) {
        	model.addAttribute("product", product);
        	model.addAttribute("cartItem", new CartItem());
        	model.addAttribute("message", insufficientQuantityMessage);
    		return "user-addtocart";
    	}
    	
		cart.getCartItemMap().get(productId).setQuantity(quantity);
    	session.setAttribute("cart", cart);
    	
		return "redirect:/";
    }
	
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cartView(Model model, HttpSession session, RedirectAttributes attributes) {
    	
    	if(session.getAttribute("cart") == null) {
    		attributes.addFlashAttribute("message", noCartMessage);
    		return "redirect:/";
    	}
    	
    	Cart cart = (Cart) session.getAttribute("cart");
    	model.addAttribute("cartItemMap", cart.getCartItemMap());
		return "user-cart";
    }

    @RequestMapping(value = "/checkout-view", method = RequestMethod.GET)
    public String checkOutView(Model model, HttpSession session, RedirectAttributes attributes) {
    	
    	if(session.getAttribute("cart") == null) {
    		attributes.addFlashAttribute("message", noCartMessage);
    		return "redirect:/";
    	}
    	
    	Cart cart = (Cart) session.getAttribute("cart");
    	model.addAttribute("cartItemMap", cart.getCartItemMap());
    	model.addAttribute("order", new Order());
    	model.addAttribute("shippingAddressList",shippingAddressService.getShippingAddressList());
    	model.addAttribute("creditCardList", creditCardService.getCreditCardList());
		return "user-checkout";
    }
    
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkOut(@Valid Order order, BindingResult result, Model model, HttpSession session) {
    	
    	if(result.hasErrors()) {
    		return "user-checkout";
    	}
    	
    	User user = (User) session.getAttribute("user");
    	Cart cart = (Cart) session.getAttribute("cart");
    	
    	order.setUser(user);
    	order.setOrderItems(orderItemService.generateOrderItemListByCart(cart));
    	
    	orderService.checkOut(order);
    	
		return "user-cart";
    }
    
    @RequestMapping(value = "/delete-cartitem", method = RequestMethod.GET)
    public String deleteCartItem(Model model, HttpSession session, @RequestParam("id") Integer id) {
    	Cart cart = (Cart) session.getAttribute("cart");
    	Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
    	
    	cartItemMap.remove(id);
    	cart.setCartItemMap(cartItemMap);
    	session.setAttribute("cart", cart);
		return "redirect:cart";
    }
	
}