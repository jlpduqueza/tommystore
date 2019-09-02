package com.tommystore.repository;

import java.util.List;

import com.tommystore.bean.LoginBean;
import com.tommystore.bean.SignUpBean;
import com.tommystore.constant.Role;
import com.tommystore.domain.User;
import com.tommystore.exceptions.InvalidSavingUserException;
import com.tommystore.exceptions.UserNotFoundException;

public interface UserRepository {

	public User findUserById(Integer id);
    public User validateLogin(LoginBean login) throws UserNotFoundException;
	public User saveUser(User user) throws InvalidSavingUserException;
	public User saveAdmin(User user) throws InvalidSavingUserException;
	public void saveAdminBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException;
    public void saveUserBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException;
    public Boolean isUserExistByEmail(String email);
    public Boolean isValidToEdit(User user);
	public List<User> getUserList() throws UserNotFoundException;
	public List<User> viewNewUser() throws UserNotFoundException;
	public List<User> findUserByRole(Role role) throws UserNotFoundException;
    
}
