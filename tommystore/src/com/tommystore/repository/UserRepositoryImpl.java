package com.tommystore.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tommystore.bean.LoginBean;
import com.tommystore.bean.SignUpBean;
import com.tommystore.constant.Role;
import com.tommystore.domain.User;
import com.tommystore.exceptions.InvalidSavingUserException;
import com.tommystore.exceptions.UserNotFoundException;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User findUserById(Integer id) {
		return em.find(User.class, id);
	}

	@Override
	public User saveUser(User user) throws InvalidSavingUserException {
		try {
	        if (user.getId() == null) {
	        	user.setRole(Role.USER);
	            em.persist(user);
	        } else {
	            user = em.merge(user);
	        }
	        return user;
		} catch (Exception e) {
			throw new InvalidSavingUserException();
		}
	}
	
	@Override
	public User saveAdmin(User user) throws InvalidSavingUserException {
		try {
	        if (user.getId() == null) {
	        	user.setRole(Role.ADMIN);
	            em.persist(user);
	        } else {
	            user = em.merge(user);
	        }
	        return user;
		} catch (Exception e) {
			throw new InvalidSavingUserException();
		}
	}
	
	@Override
	public void saveAdminBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException {
		User user = new User();
		user.setEmail(signUpBean.getEmail());
		user.setPassword(signUpBean.getPassword());
		user.setContactNumber(signUpBean.getContactNumber());
		user.setFirstName(signUpBean.getFirstName());
		user.setLastName(signUpBean.getLastName());
		
		saveAdmin(user);
	}

	@Override
	public User validateLogin(LoginBean login) throws UserNotFoundException {
		try {
			TypedQuery<User> query =  em.createQuery("From User where email = :email AND password = :pass", User.class);
			query.setParameter("email", login.getEmail());
			query.setParameter("pass", login.getPassword());
			
	        return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveUserBySignUp(SignUpBean signUpBean) throws InvalidSavingUserException {
		User user = new User();
		//note put this to one method 
		user.setEmail(signUpBean.getEmail());
		user.setPassword(signUpBean.getPassword());
		user.setContactNumber(signUpBean.getContactNumber());
		user.setFirstName(signUpBean.getFirstName());
		user.setLastName(signUpBean.getLastName());
		
		saveUser(user);
	}

	@Override
	public List<User> getUserList() throws UserNotFoundException {
		try {
			TypedQuery<User> query =  em.createQuery("From User", User.class);
			return query.getResultList();
		} catch (NoResultException e) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public List<User> viewNewUser() throws UserNotFoundException {
		Date currentDate = new Date();
		Timestamp oneDayInterval = new Timestamp(currentDate.getTime() - (24*60*60*1000));
		
		try {
			TypedQuery<User> query = em.createQuery("FROM User WHERE createDateTime >= :oneDayInterval", User.class);
			query.setParameter("oneDayInterval", oneDayInterval);
			return query.getResultList();
		} catch (NoResultException e) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public List<User> findUserByRole(Role role) throws UserNotFoundException {
		try {
			TypedQuery<User> query =  em.createQuery("From User where role=:role", User.class);
			query.setParameter("role", role);
			return query.getResultList();
		} catch (NoResultException e) {
			throw new UserNotFoundException();
		}
	}

	@Override
	public Boolean isUserExistByEmail(String email) {
		TypedQuery<User> query =  em.createQuery("From User where email = :email", User.class);
		query.setParameter("email", email);
		
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public Boolean isValidToEdit(User user) {
		TypedQuery<User> query =  em.createQuery("From User where email = :email AND id != :id", User.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("id", user.getId());
		
		try {
			query.getSingleResult();
			return false;
		} catch (NoResultException e) {
			return true;
		}
	}
	
}
