package com.spring.security.ldap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

	@Value("${userDnPatterns}")
	private String userDnPatterns;
	
	@Value("${groupSearchBase}")
	private String groupSearchBase;
	
	@Value("${ldapUrl}")
	private String ldapUrl;
	
	@Value("${ldapPassword}")
	private String ldapPassword;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.ldapAuthentication()
		.userDnPatterns(userDnPatterns)
		.groupSearchBase(groupSearchBase)
		.contextSource()
		.url(ldapUrl)
		.and().passwordCompare()
		.passwordEncoder(new LdapShaPasswordEncoder())
		.passwordAttribute(ldapPassword);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin();
	}

}
