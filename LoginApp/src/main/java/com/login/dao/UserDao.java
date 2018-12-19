package com.login.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>
{
	List<User> findByUserNameAndPassword(String name,String password);
	
	
}
