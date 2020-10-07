package com.mylearning.springboot.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person2 {

	private Name name;

	@Override
	public String toString() {
		return "Person2 [name=" + name + "]";
	}
	
}
