package com.project.Oauth2AndJwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class Oauth2AndJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AndJwtApplication.class, args);
	}
	
	@RequestMapping(value="/product")
	public String getProductname() {
		return "Honey";
	}

}
