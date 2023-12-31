package com.isteer.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isteer.dao.layer.UserDao;
import com.isteer.exception.UserIdNotFoundException;
import com.isteer.module.User;
import com.isteer.services.UserService;

@Service
public class UsersDetailsServices implements UserDetailsService {

	@Autowired
	UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = dao.getUserByUserName(username);
		System.out.println("hiii");
		if (user == null) {
			List<String> exception = new ArrayList<String>();
			exception.add("User Name Not Found");
			throw new UserIdNotFoundException(0, "Failed", exception);
		}

		return new Principles(user);
	}

}
