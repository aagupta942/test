package com.mylearning.springboot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mylearning.springboot.bo.FilteringExampleBean;

@RestController
public class DynamicFilteringController {

	@GetMapping(path = "/dynamic-filter-bean")
	public MappingJacksonValue filterBean() {
		FilteringExampleBean filteringExampleBean = new FilteringExampleBean("value1", "value2", "value3");

		MappingJacksonValue mapping = new MappingJacksonValue(filteringExampleBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value1","value2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("filteringExampleBean", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping(path = "/dynamic-filter-bean-list")
	public MappingJacksonValue filterBeanList() {
		List<FilteringExampleBean> filteringExampleBean = Arrays.asList(new FilteringExampleBean("value1", "value2", "value3"),new FilteringExampleBean("value11", "value22", "value33"));

		MappingJacksonValue mapping = new MappingJacksonValue(filteringExampleBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2","value3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("filteringExampleBean", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}


}
