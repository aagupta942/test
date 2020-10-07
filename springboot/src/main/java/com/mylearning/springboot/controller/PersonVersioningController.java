package com.mylearning.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.springboot.bo.Name;
import com.mylearning.springboot.bo.Person1;
import com.mylearning.springboot.bo.Person2;

@RestController
public class PersonVersioningController {

	//Request Parameter Versioning
	@GetMapping(path = "person/param", params = "version=1")
	public Person1 person1() {
		return new Person1("Bob charlie");
	}
	
	@GetMapping(path = "person/param", params = "version=2")
	public Person2 person2() {
		return new Person2(new Name("Bob", "Charlie"));
	}
	
	//header versioning
	@GetMapping(path = "person/header", headers = "X-API-VERSION=1")
	public Person1 header1() {
		return new Person1("Bob charlie");
	}
	
	@GetMapping(path = "person/header", headers = "X-API-VERSION=2")
	public Person2 header2() {
		return new Person2(new Name("Bob", "Charlie"));
	}
	
	//produces or accept or negotiation versioning
	@GetMapping(path = "person/produces", produces = "application/ayush.com-v1+json")
	public Person1 produces1() {
		return new Person1("Bob charlie");
	}
	
	@GetMapping(path = "person/produces", produces = "application/ayush.com-v2+json")
	public Person2 produces2() {
		return new Person2(new Name("Bob", "Charlie"));
	}
}
