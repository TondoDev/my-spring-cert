package org.tondo.bootms.rws.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tondo.bootms.rws.beans.AnotherFilteredBean;
import org.tondo.bootms.rws.beans.FilteredBean;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	
	@GetMapping("/filtered")
	public FilteredBean geFilteredObject() {
		
		return new FilteredBean("ahoj", "tonko", "heslo");
	}
	
	@GetMapping("/filtered-list")
	public List<FilteredBean> getListofFiltered() {
		return Arrays.asList(new FilteredBean("dobry", "Janko", "pes"),
				new FilteredBean("kuk", "Misko", "haf"));
	}
	
	@GetMapping("/dynamicfilter-list")
	public MappingJacksonValue dynamicFilterList() {
		List<AnotherFilteredBean> values = Arrays.asList(new AnotherFilteredBean("raz", "dva", "tri"),
				new AnotherFilteredBean("styri", "dva", "tri"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("second", "third");
		// id must match with annotation on  AnotherFilteredBean class
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFiler", filter);
		//spring
		MappingJacksonValue mapping = new MappingJacksonValue(values);
		mapping.setFilters(filters);
		return mapping;
	}
	
	
	@GetMapping("/dynamicfilter")
	public MappingJacksonValue dynamicFilter() {
		AnotherFilteredBean bean = new AnotherFilteredBean("raz", "dva", "tri");
		// jackson - this fields will be serialized
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("first", "third");
		// id must match with annotation on  AnotherFilteredBean class
		FilterProvider filters = new SimpleFilterProvider().addFilter("dynamicFiler", filter);
		//spring
		MappingJacksonValue mapping = new MappingJacksonValue(bean);
		mapping.setFilters(filters);
		return mapping;
	}
}
