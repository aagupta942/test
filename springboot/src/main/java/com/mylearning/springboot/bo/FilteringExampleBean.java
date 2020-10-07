package com.mylearning.springboot.bo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter(value = "filteringExampleBean") //Dynamic filtering
//@JsonIgnoreProperties(value = "value3") //static filtering
public class FilteringExampleBean {
	
	private String value1;
	
	private String value2;
	
	//@JsonIgnore //static filtering
	private String value3;
	
	@Override
	public String toString() {
		return "FilteringExampleBean [value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + "]";
	}

}
