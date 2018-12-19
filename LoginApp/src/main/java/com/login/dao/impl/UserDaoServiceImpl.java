package com.login.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.login.dao.UserDao;
import com.login.dao.UserDaoService;
import com.login.model.User;
import com.login.model.UserDto;

@Service
public class UserDaoServiceImpl implements UserDaoService{
	
	private static final Logger log= LogManager.getLogger(UserDaoServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @Autowired
	 private AuthenticationManager authenticationManager;
	
	public User appendUser(UserDto userDto) {
		if(userDto != null)
		{
		 User user = new User();
		 user.setUserName(userDto.getUsername());
		 String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
		 user.setPassword(encodedPassword);
		 user.setEmail(userDto.getEmail());
		 return user;
		}
		else
		{
			return null;
		}
	}

	@Override
	public User saveUser(User user) {
		try
		{
			return userDao.save(user);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String authenticateUser(UserDto userDto){
		
		log.info("authenticateUser....start");
		
		if(userDto.getUsername() != null && userDto.getPassword() != null)
		{
			try
			{
			//return userDao.findByUserNameAndPassword(userDto.getUsername(),userDto.getPassword());
				return autologin(userDto.getUsername(),userDto.getPassword());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.warn(e.getMessage());
			}
			
		}
		return null;
	}
	 @Override
	    public String autologin(String username, String password) {
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password,null);

	        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            log.debug(String.format("Auto login %s successfully!", username));
	            return userDetails.getUsername();
	           
	        }
			return null;
	    }
	
}
