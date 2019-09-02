package com.tommystore.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tommystore.bean.LoginBean;
import com.tommystore.bean.SignUpBean;
import com.tommystore.constant.Role;
import com.tommystore.domain.User;
import com.tommystore.exceptions.DataAccessException;
import com.tommystore.exceptions.InvalidSavingUserException;
import com.tommystore.exceptions.UserNotFoundException;
import com.tommystore.service.UserService;

@Controller
@PropertySource("/WEB-INF/properties")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Value("${invalid.user}")
	private String errorMessage;
	
	@Value("${invalid.emailUsed}")
	private String emailUsedMessage;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initForm(Model model, HttpSession session) throws DataAccessException {
    	User user = (User) session.getAttribute("user");
    	if(user == null) {
    		return "home";
    	}
    	if(userService.findUserById(user.getId()).getRole().equals(Role.ADMIN)) {
    		return "redirect:/admin/dashboard";
    	}
		return "home";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
    	model.addAttribute("loginBean", new LoginBean());
		return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
    	session.invalidate();
		return "redirect:/";
    }
    
    @RequestMapping(value = "/logging-in", method = RequestMethod.POST)
    public String loggingIn(@Valid LoginBean login, BindingResult result, Model model, HttpSession session, RedirectAttributes attributes) throws UserNotFoundException {
    	
        if (result.hasErrors()) {
        	attributes.addFlashAttribute("message", errorMessage);
            return "redirect:login";
        }	
        
        User user = userService.validateLogin(login); 
        
        if(user == null) {
        	attributes.addFlashAttribute("message", errorMessage);
            return "redirect:login";
        }
        
    	session.setAttribute("user", user);
    	return "redirect:/";
        
    }
    
    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public String signUp(Model model) {
    	
    	model.addAttribute("signUpBean", new SignUpBean());
		return "sign-up";
    }
    
    @RequestMapping(value = "/signing-up", method = RequestMethod.POST)
    public String signingUp(@Valid SignUpBean signUpBean, BindingResult result, Model model, HttpSession session) throws InvalidSavingUserException {
    	
        if(!signUpBean.getConfirmPassword().equals(signUpBean.getPassword())) {
        	result.rejectValue("confirmPassword", "error.signUpBean", "Those password didn't match.");
        }
    	
        if (result.hasErrors()) {
            return "sign-up";
        }
        
        if(userService.isUserExistByEmail(signUpBean.getEmail())) {
        	model.addAttribute("message", emailUsedMessage);
        	return "sign-up";
        }
        
        userService.saveUserBySignUp(signUpBean);
		return "redirect:login";
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundHandler(Exception ex, Model model, RedirectAttributes attributes) {
        
    	attributes.addFlashAttribute("message", errorMessage);
        return "redirect:login";
    }
}
