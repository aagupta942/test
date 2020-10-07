package com.mylearning.springboot.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person1 {

	private String name;

	@Override
	public String toString() {
		return "Person1 [name=" + name + "]";
	}
	
}
