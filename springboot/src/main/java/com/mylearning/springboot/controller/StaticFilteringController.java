package com.mylearning.springboot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mylearning.springboot.bo.FilteringExampleBean;

@RestController
public class StaticFilteringController {

	@GetMapping(path = "/filtering-bean")
	public FilteringExampleBean getBean() {
		return new FilteringExampleBean("value1", "value2", "value3");
	}

	@GetMapping(path = "/filtering-bean-list")
	public List<FilteringExampleBean> getBeanList() {
		return Arrays.asList(new FilteringExampleBean("value1", "value2", "value3"),
				new FilteringExampleBean("value11", "value22", "value33"));
	}

}
