package com.learning.mylearning.resource;

import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceClass {

	@PostMapping(path = "/hello-world")
	public String helloWorld(BearerTokenAuthentication bearerTokenAuthentication,
			@RequestHeader(value = "Authorization") String bearerToken) {
		System.out.println(bearerTokenAuthentication.getToken().getTokenValue());
		return "Hello World";
	}

}
