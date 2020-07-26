package com.project.Oauth2AndJwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.Oauth2AndJwt.CustomUser.CustomUser;
import com.project.Oauth2AndJwt.Entity.UserEntity;
import com.project.Oauth2AndJwt.Repository.OauthDao;

@Service
public class CustomDetailsService implements UserDetailsService {
	
	@Autowired
	OauthDao oauthDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity= null;
		
		try {
			userEntity= oauthDao.getUserDetails(username);
			CustomUser customUser= new CustomUser(userEntity);
			return customUser;
		}catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User "+ username + "was not found in the database");
		}
	}

}
