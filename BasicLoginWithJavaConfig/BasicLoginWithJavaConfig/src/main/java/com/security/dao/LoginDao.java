package com.security.dao;

import java.util.List;

import com.security.model.UserInfo;

public interface LoginDao 
{
   public UserInfo fineUserInfo(String username);
   
   public List<String> getUserRoles(String username);
}
