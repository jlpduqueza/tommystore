package com.tommystore.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tommystore.bean.CreditCardBean;
import com.tommystore.bean.ShippingAddressBean;
import com.tommystore.domain.CreditCard;
import com.tommystore.domain.ShippingAddress;
import com.tommystore.domain.User;
import com.tommystore.service.CreditCardService;
import com.tommystore.service.ShippingAddressService;

@Controller
@RequestMapping(value = "/user")
@PropertySource("/WEB-INF/properties")
public class UserController {

	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
    @RequestMapping(value = "/my-account", method = RequestMethod.GET)
    public String myAccount(Model model) {
    	model.addAttribute("shippingAddressList", shippingAddressService.getShippingAddressList());
    	model.addAttribute("creditCardList", creditCardService.getCreditCardList());
		return "user-myaccount";
    }
    
    @RequestMapping(value = "/add-address-view", method = RequestMethod.GET)
    public String addAddress(Model model) {
    	model.addAttribute("countryList", shippingAddressService.getCountryList());
    	model.addAttribute("shippingAddressBean", new ShippingAddressBean());

		return "user-add-address";
    }
    
    @RequestMapping(value = "/add-address", method = RequestMethod.POST)
    public String addAddressView(@Valid ShippingAddressBean shippingAddressBean, BindingResult result, Model model, HttpSession session) {
    	
    	if(result.hasErrors()) {
    		model.addAttribute("message", "someMessage");
    		return "redirect:add-credit-card-view";
    	}
    	
    	ShippingAddress shippingAddress = new ShippingAddress();
    	shippingAddress.setAddress1(shippingAddressBean.getAddress1());
    	shippingAddress.setAddress2(shippingAddressBean.getAddress2());
    	shippingAddress.setCountry(shippingAddressBean.getCountry());
    	shippingAddress.setZipCode(shippingAddressBean.getZipCode());
    	shippingAddress.setUser((User) session.getAttribute("user"));

    	shippingAddressService.saveShippingAddress(shippingAddress);
    	return "redirect:my-account";
    }
    
    @RequestMapping(value = "/add-credit-card-view", method = RequestMethod.GET)
    public String addCreditCardView(Model model) {
    	model.addAttribute("creditCardBean", new CreditCardBean());
    	
		return "user-add-creditcard";
    }
    
    @RequestMapping(value = "/add-credit-card", method = RequestMethod.POST)
    public String addCreditCard(@Valid CreditCardBean creditCardBean, BindingResult result, Model model, HttpSession session) {
    	
    	if(result.hasErrors()) {
    		model.addAttribute("message", "someMessage");
    		return "redirect:add-credit-card-view";
    	}
    	
    	CreditCard creditCard = new CreditCard();
    	creditCard.setCardNumber(creditCardBean.getCardNumber());
    	creditCard.setSecurityCode(creditCardBean.getSecurityCode());
    	creditCard.setUser((User) session.getAttribute("user"));
    	
    	creditCardService.saveCreditCard(creditCard);
    	
    	return "redirect:my-account";
    }
    
    @RequestMapping(value = "/delete-credit-card", method = RequestMethod.GET)
    public String deleteCreditCard(Model model, @RequestParam("id") Integer id) {
    	creditCardService.deleteCreditCardById(id);
    	
		return "redirect:my-account";
    }
    
    @RequestMapping(value = "/delete-shipping-address", method = RequestMethod.GET)
    public String deleteShippingAddress(Model model, @RequestParam("id") Integer id) {
    	shippingAddressService.deleteShippingAddressById(id);
    	
		return "redirect:my-account";
    }
    
    @RequestMapping(value = "/credit-card-view", method = RequestMethod.GET)
    public String creditCardView(Model model) {
    	model.addAttribute("creditCardList", creditCardService.getCreditCardList());
    	
		return "user-creditcard-view";
    }

    @RequestMapping(value = "/shipping-address-view", method = RequestMethod.GET)
    public String addressView(Model model) {
    	model.addAttribute("shippingAddressList", shippingAddressService.getShippingAddressList());
    	
		return "user-shippingaddress-view";
    }
	
	
}