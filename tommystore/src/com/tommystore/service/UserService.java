package com.tommystore.service;

import java.util.List;

import com.tommystore.bean.LoginBean;
import com.tommystore.bean.SignUpBean;
import com.tommystore.constant.Role;
import com.tommystore.domain.User;
import com.tommystore.exceptions.InvalidSavingUserException;
import com.tommystore.exceptions.UserNotFoundException;

public interface UserService {
	
	public User findUserById(Integer id);
	public User saveUser(User user) throws InvalidSavingUserException;
	public User validateLogin(LoginBean login) throws UserNotFoundException;
    public void saveUserBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException;
    public void saveAdminBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException;
    public Boolean isUserExistByEmail(String email);
    public Boolean isValidToEdit(User user);
    public List<String> getRoleList();
	public List<User> getUserList() throws UserNotFoundException;
	public List<User> viewNewUser() throws UserNotFoundException;
	public List<User> findUserByRole(Role role) throws UserNotFoundException;
}
