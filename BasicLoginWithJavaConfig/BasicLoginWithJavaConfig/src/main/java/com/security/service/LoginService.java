package com.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.security.dao.LoginDao;
import com.security.model.UserInfo;

public class LoginService implements UserDetailsService {
	
	LoginDao loginDao;
	
	@Autowired
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}



	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo userInfo  =  loginDao.fineUserInfo(username);
		if(userInfo == null)
		{
			throw new UsernameNotFoundException("username was not found in the database");
		}
		List<String> roles  = loginDao.getUserRoles(username);
		List<GrantedAuthority> grantedList  = new ArrayList<GrantedAuthority>();
		if(roles != null)
		{
			for(String role : roles)
			{
				GrantedAuthority auth = new SimpleGrantedAuthority(role);
				grantedList.add(auth);
			}
		}
		UserDetails userDetails  = new User(userInfo.getUsername(),userInfo.getPassword(),grantedList);
		// TODO Auto-generated method stub
		return userDetails;
	}

}
