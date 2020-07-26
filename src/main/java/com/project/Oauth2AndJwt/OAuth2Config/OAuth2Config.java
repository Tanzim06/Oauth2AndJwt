package com.project.Oauth2AndJwt.OAuth2Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	
	private String clientId="tutorialspoint";
	private String clientSecret="1234";
	private String privateKey= "MIIEowIBAAKCAQEAz2YLN7Z1wLdCKyzEYAGKTT6ZrOJMNoMvV8DFTgueSx+tbSX2\r\n" + 
			"uCNB/pspExiBEIIAYOdzr/O63CH5tZ7yBbeZekmye2BKbzTu8lPujNBjhU6c6N5J\r\n" + 
			"66kw8mnmSaMjgoIIaADbbWngsFhM1qYAFNKk3ZlijWuRi04okSRBkUsiwA/VbZcm\r\n" + 
			"LUKRrHJgKkd8e6z3pO6KndlpTek4HqFKkEBLnvkrGUD+YuUabjC4Wk9rloDI2hUD\r\n" + 
			"XrOL2cw+OIW5dK+E8eXz6D3n6qTCPuee/tUj18+Be8NUX1pYuDYTSvU+Fej4xLO3\r\n" + 
			"BGzHKgLIQuX9elZRr1p1agLnTP56ooeONNt7xQIDAQABAoIBAC+4E+aqtac0wyW5\r\n" + 
			"9Cj+0bp6J9OuccitIGx5byxI4g/45udRL9YoTpvtSWMaZT/OGZYyStz2IE1cB99x\r\n" + 
			"BdCo8MhBQq6+dgHmVQAurZl6rRtT/ihaVPnDAP8VvKW+woHZt2Nupw4iqU8J0gwl\r\n" + 
			"P6mK2eIe050kXeWGgzD5/ZgXiJRV5kXIgkQ3AlifH3OS/Eyv8y2JqL5xrQLlzWEV\r\n" + 
			"QjIT38ILzgwlVl1tD5olEL5sDnkJ3gejiZrsGUKQLJdM+F7D/uIX34q1COrR89dy\r\n" + 
			"cfdUzK8WdAJd6TAtcgi1QyjmeTwZ/sYMD1rhkaQYcSa820D53mPx5AxSwpvglWt8\r\n" + 
			"t21rhmECgYEA5pAwaWWRz2FoT+iRnbEuWXk8dl9iUiHIlYQ5dJmu1PH4TXTdZXnL\r\n" + 
			"1S9f+EfNB8+q5tKZvNwv83ggiugkJNrZkCGljP6Kczq1eyLtuJMIo7qFvxKFM+0p\r\n" + 
			"sCW/WM7Xg4A+8wrLk0YYslmSUGNFzwWbY0XV+ZspqA+zCpIZXcplRC0CgYEA5ked\r\n" + 
			"Xo+cI5iVXFHmOUBYsWXLZ4QDjXXLgatSM7kKnbU1+BOZuqX6+4sq88sw9/gzFj4f\r\n" + 
			"JxgWszYZhg/Bl6lLu8S2D4EPdkBpzvAADkmX+Po19lj61iKXBxlPH23GzZKJU4hA\r\n" + 
			"S7Ejbr+Ysz1sxkT4mnrUXJmlKXa5t4mG4CDMXPkCgYA17pCEdu/OlLhzyLEXM/Tl\r\n" + 
			"lQhhoBo9oA1tfAp8jd5pjVhJtgZ70Cuv6G8IgrMhGqCdcbHojZDh4L2sAHVD8bVm\r\n" + 
			"k5hIDNqR7a+56Ae3Xx1sPxs+ALUL2nQf8hR6T5f1+K/A5kuMXZ81SOKCz1XrASLB\r\n" + 
			"6/oLcxIhb9qvpFEusUNS2QKBgQCU9wDTOf1cfYkpZoWSxZBaeapCNYEC0o+qO0R6\r\n" + 
			"1xoJ+T6HU06gEl0wuc84VFpCHtoVamxGouuUVMvgEHNAg7DNL6xCjU+ORI7t4g13\r\n" + 
			"MJ5R3rJnwRnKyid+3R11DFONilyxn7NQA7ATJXhHgtXJ0LYtYIXdvTq9fuMW2q1Y\r\n" + 
			"cX8iKQKBgHDXrGElA0RVHaG2h6ri7xirKjrl5PV3CkYIVxvJqhUHkdPIaiQhCDfG\r\n" + 
			"U3BwyexQHunhhLulXJi1lhv0sOQXyZ0otWh7iRs7uFs1x3ofrl43ukXDMihropQn\r\n" + 
			"G9tLEc847dBMZgRXeqyY/23rJCkb4M0EgsV0u/06PuzxjWWvsmIU";
	
	private String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz2YLN7Z1wLdCKyzEYAGK\r\n" + 
			"TT6ZrOJMNoMvV8DFTgueSx+tbSX2uCNB/pspExiBEIIAYOdzr/O63CH5tZ7yBbeZ\r\n" + 
			"ekmye2BKbzTu8lPujNBjhU6c6N5J66kw8mnmSaMjgoIIaADbbWngsFhM1qYAFNKk\r\n" + 
			"3ZlijWuRi04okSRBkUsiwA/VbZcmLUKRrHJgKkd8e6z3pO6KndlpTek4HqFKkEBL\r\n" + 
			"nvkrGUD+YuUabjC4Wk9rloDI2hUDXrOL2cw+OIW5dK+E8eXz6D3n6qTCPuee/tUj\r\n" + 
			"18+Be8NUX1pYuDYTSvU+Fej4xLO3BGzHKgLIQuX9elZRr1p1agLnTP56ooeONNt7\r\n" + 
			"xQIDAQAB";
	
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	
	@Bean
	public ResourceServerTokenServices tokenEnhancer(){
		JwtAccessTokenConverter converter= new JwtAccessTokenConverter();
		
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return (ResourceServerTokenServices)converter;
	}
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore((JwtAccessTokenConverter) tokenEnhancer());
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
		.accessTokenConverter((AccessTokenConverter) tokenEnhancer());
	}
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory().withClient(clientId).secret(clientSecret).scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(2000)
		.refreshTokenValiditySeconds(2000);
		}
	
	
}