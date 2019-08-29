package com.tommystore.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.tommystore.bean.RoleFilterBean;
import com.tommystore.bean.SignUpBean;
import com.tommystore.constant.Role;
import com.tommystore.domain.User;
import com.tommystore.service.InventoryItemService;
import com.tommystore.service.UserService;

@Controller
@RequestMapping(value = "/admin")
@PropertySource("/WEB-INF/properties")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InventoryItemService inventoryItemService;
	
	@Value("${inventory.nstock}")
	private int nStock;

    @RequestMapping(value = "/user-list-view", method = RequestMethod.GET)
    public String login(Model model) {
    	List<String> roleList = new ArrayList<>();
    	roleList.add("All");
    	
    	for (Role role : Role.values()) {
    		roleList.add(role.toString());
    	}
    	
    	model.addAttribute("userList", userService.getUserList());
    	model.addAttribute("roleBean", new RoleFilterBean());
    	model.addAttribute("roleList", roleList);
    	
		return "admin-dashboard-userlist";
    }
    
    @RequestMapping(value = "/filter-user", method = RequestMethod.POST)
    public String filterUser(@Valid RoleFilterBean roleBean, Model model) {
    	List<String> roleList = new ArrayList<>();
    	roleList.add("All");
    	
    	for (Role role : Role.values()) {
    		roleList.add(role.toString());
    	}
    	
    	String role = roleBean.getRole();
    	
    	if(!role.equals("ADMIN") || role.equals("USER")) {
        	return "redirect:user-list-view";
    	}
    	
    	model.addAttribute("userList", userService.findUserByRole(Role.valueOf(roleBean.getRole())));
    	model.addAttribute("roleBean", new RoleFilterBean());
    	model.addAttribute("roleList", roleList);
    	model.addAttribute("role", roleBean.getRole());
    	return "admin-dashboard-userlist";
    }
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
    	model.addAttribute("userList", userService.viewNewUser());
    	model.addAttribute("inventoryItemList", inventoryItemService.findInventoryItemListByStock(nStock));
		return "admin-dashboard";
    }
    
    @RequestMapping(value = "/add-admin-view", method = RequestMethod.GET)
    public String addAdministratorView(Model model) {
    	model.addAttribute("signUpBean", new SignUpBean());
		return "admin-dashboard-add-administrator";
    }
    
    @RequestMapping(value = "/add-admin", method = RequestMethod.POST)
    public String addAdministrator(@Valid SignUpBean signUpBean, BindingResult result, Model model, HttpSession session) {
    	
        if(!signUpBean.getConfirmPassword().equals(signUpBean.getPassword())) {
        	result.rejectValue("confirmPassword", "error.signUpBean", "Those password didn't match.");
        }
    	
        if (result.hasErrors()) {
            return "sign-up";
        }	
        
        userService.saveAdminBySignUp(signUpBean);
		return "redirect:dashboard";
    }
    
    @RequestMapping(value = "/edit-user-view", method = RequestMethod.GET)
    public String editUserView(Model model, @RequestParam("id") Integer userId) {
    	User user = userService.findUserById(userId);
    	model.addAttribute("userToEdit", user);
		return "admin-dashboard-edit-user";
    }
    
    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public String editUser(@Valid User user, BindingResult result,Model model) {
    	
        if (result.hasErrors()) {
            return "sign-up";
        }	
        
        userService.saveUser(user);
		return "redirect:dashboard";
    }
    
}
