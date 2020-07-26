package com.project.Oauth2AndJwt.CustomUser;

import org.springframework.security.core.userdetails.User;

import com.project.Oauth2AndJwt.Entity.UserEntity;;

public class CustomUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	public CustomUser(UserEntity user) {
		super(user.getUsername(), user.getPasword(), user.getGrantedAuthoritiesList());
		
	}
}
