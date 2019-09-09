package com.tommystore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tommystore.bean.LoginBean;
import com.tommystore.bean.SignUpBean;
import com.tommystore.constant.Role;
import com.tommystore.domain.User;
import com.tommystore.exceptions.InvalidSavingUserException;
import com.tommystore.exceptions.UserNotFoundException;
import com.tommystore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User findUserById(Integer id) {
		return userRepository.findUserById(id);
	}

	@Override
	@Transactional
	public User saveUser(User user) throws InvalidSavingUserException {
		return userRepository.saveUser(user);
	}

	@Override
	@Transactional
	public User validateLogin(LoginBean login) throws UserNotFoundException {
		return userRepository.validateLogin(login);
	}

	@Override
	@Transactional
	public void saveUserBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException {
		userRepository.saveUserBySignUp(signUpBean);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getUserList() throws UserNotFoundException {
		return userRepository.getUserList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> viewNewUser() throws UserNotFoundException {
		return userRepository.viewNewUser();
	}

	@Override
	@Transactional
	public void saveAdminBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException {
		userRepository.saveAdminBySignUp(signUpBean);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findUserByRole(Role role) throws UserNotFoundException {
		return userRepository.findUserByRole(role);
	}

	@Override
	@Transactional
	public Boolean isUserExistByEmail(String email) {
		return userRepository.isUserExistByEmail(email);
	}

	@Override
	public List<String> getRoleList() {
    	List<String> roleList = new ArrayList<>();
    	roleList.add("All");
    	
    	for (Role role : Role.values()) {
    		roleList.add(role.toString());
    	}
    	return roleList;
	}

	@Override
	@Transactional
	public Boolean isValidToEdit(User user) {
		return userRepository.isValidToEdit(user);
	}

}
