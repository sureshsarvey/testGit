package com.login.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.login.dao.impl.UserDaoServiceImpl;
import com.login.exception.AuthenticationException;
import com.login.model.User;
import com.login.model.UserDto;

@Controller
@RequestMapping("/loginController")
public class LoginController {
	
	 private static final Logger log = LogManager.getLogger(LoginController.class);
	
	@Autowired
	public UserDaoServiceImpl userDaoServiceImpl;
	
	

	  @RequestMapping("/")
	   public String home(Map<String, Object> model) {
		    log.info("home.....method");
	        model.put("message", "This is index page !!");
	        return "index";
	    }
	   
	  @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = "application/json")
	  public ResponseEntity<?> registerUser(@RequestBody UserDto userDto, HttpServletRequest request,ModelMap map)
	  {
		  if(request != null)
		  { 
			  log.info("loginController. registerUser()..... start");
			  User user = userDaoServiceImpl.appendUser(userDto);
			  if(user != null)
			  {
				  try {
					  userDaoServiceImpl.saveUser(user);
					  log.info(user.getUserName() +" Registered in successfully");
				  }catch(Exception e)
				  {
					  e.printStackTrace();
					  log.error(user.getUserName() +" Registration failed", e);
					  return new ResponseEntity<UserDto>(userDto, HttpStatus.INTERNAL_SERVER_ERROR);
				  }
			  }
			  log.info("loginController. registerUser()..... end");
			  return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		  }
		  else
		  {
			  log.error("user must login");
			  return new ResponseEntity<List<?>>(HttpStatus.FORBIDDEN);
		  }
	  }
	  @RequestMapping(value = "/loginUser", method = RequestMethod.POST, produces = "application/json")
	  public ResponseEntity<?> loginUser(@RequestBody UserDto userDto, HttpServletRequest request,ModelMap map)
	  {
		  if(request != null)
		  { 
			  log.info("loginController. loginUser()..... start");
			  if(userDto != null)
			  {
				  try {
					  String user = userDaoServiceImpl.authenticateUser(userDto);
					  if(!StringUtils.isEmpty(user))
					  {
						 /* user.forEach(us ->{
							  log.info(us.getUserName() +" logged in successfully");
						  });*/
						  log.info(user +" logged in successfully");
					  }
					  else {
						  throw new AuthenticationException("username or password is incorrect");
					  }
				  }catch(Exception e)
				  {
					  e.printStackTrace();
					  log.error(e.getMessage(), e);
					  return new ResponseEntity<UserDto>(userDto, HttpStatus.INTERNAL_SERVER_ERROR);
				  }
			  }
			  log.info("loginController. loginUser().....end");
			  return new ResponseEntity<UserDto>(HttpStatus.OK);
		  }
		  else
		  {
			  log.error("user must login");
			  return new ResponseEntity<List<?>>(HttpStatus.FORBIDDEN);
		  }
	  }
}
