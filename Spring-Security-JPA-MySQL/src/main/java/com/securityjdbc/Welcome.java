package com.securityjdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
	
	@GetMapping("/")
	public String getMessage () {
		return ("<h1>Welcome</h1>");
		
	}
	
	@GetMapping("/admin")
	public String getMessageAdmin () {
		return ("<h1>Welcome Admin</h1>");
		
	}
	
	
	@GetMapping("/user")
	public String getMessageUser () {
		return ("<h1>Welcome User</h1>");
		
	}
}
