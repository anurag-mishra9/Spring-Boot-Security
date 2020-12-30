package com.securityjdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		/* Using JPA + MySql Database + UserDetailsService*/
		auth.userDetailsService(userDetailsService);
		
		/* Using Custom Datasource like oracle with autowired dependency and application.properties updated
		 * auth.jdbcAuthentication().dataSource(dataSource); */
		
		/*
		 * For Custom query use below logic
		 * auth.jdbcAuthentication().dataSource(dataSource)
		 * .usersByUsernameQuery("	query ").authoritiesByUsernameQuery(" query ");
		 */

		/*
		 * For Default Spring Security data // 
		 * auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
		 */
		
		
		/* In Memory Authentication 
		 * authenticationManagerBuilder.inMemoryAuthentication()
		 * .withUser("anurag").password("mishra").roles("USER")
		 * .and().withUser("anu").password("mis").roles("ADMIN");
		 */
	}

	@Bean
	public PasswordEncoder getPasswordEncoder () {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user").hasRole("USER")
		//.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/admin").hasAnyRole("ADMIN","USER") // allow both roles for admin 
		.antMatchers("/").permitAll()
		.and().formLogin();
	}

}
