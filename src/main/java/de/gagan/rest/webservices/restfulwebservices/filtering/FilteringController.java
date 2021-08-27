package de.gagan.rest.webservices.restfulwebservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filterBean")
	public FilterBean getFilterBean() {
		return new FilterBean("val1","val2","val3");
	}
	
	@GetMapping("/dynamicFilterBean")
	public MappingJacksonValue getDynamicFilterBean() {
		FilterBean filterBean = new FilterBean("val1","val2","val3"); 
		MappingJacksonValue jacksonValue = new MappingJacksonValue(filterBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("simpleFilter", filter);
		jacksonValue.setFilters(filterProvider);
		return jacksonValue;
	}
}
