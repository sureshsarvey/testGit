package com.login.dao;

import java.util.List;

import com.login.exception.AuthenticationException;
import com.login.model.User;
import com.login.model.UserDto;

public interface UserDaoService {

	public User appendUser(UserDto userDto);
	
	public User saveUser(User user);
	
	public String authenticateUser(UserDto userDto);
	
	public String autologin(String username, String password);
}
