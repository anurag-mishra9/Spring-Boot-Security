package com.securityjdbc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.securityjdbc.models.MyUserDetails;
import com.securityjdbc.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* Creating a JPA repository to get the details of UserDetails from database (MySql)*/
		Optional<User> user = userRepository.findUserByName(username);
		user.orElseThrow(() -> new UsernameNotFoundException("Not Found : "+username));
		return user.map(MyUserDetails::new).get();
		
		/* Creating an instance of UserDetails Object and populating hardcoded details in the implementation UserDetails(MyUserDetails)
		 * return new MyUserDetails(username); */
	}
	

}
