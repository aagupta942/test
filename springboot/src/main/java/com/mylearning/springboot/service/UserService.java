package com.mylearning.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mylearning.springboot.bo.User;

@Component
public class UserService {

	private static List<User> users = new ArrayList<>();
	private static int count=3;
	/*
	 * static { users.add(new User("John",new Date(), 1)); users.add(new
	 * User("Adam",new Date(), 2)); users.add(new User("Smith",new Date(), 3)); }
	 */
	
	public List<User> findAllUsers(){
		return users;
	}

	public User findById(Integer id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}

	public User addUser(User user) {
		if(user.getId()==null) {
			user.setId(++count);
		}
		users.add(user);
		return user;
	}

	public User deleteUser(Integer id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId()==id) {
				users.remove(user);
				return user;
			}
		}
		return null;
	}
}
