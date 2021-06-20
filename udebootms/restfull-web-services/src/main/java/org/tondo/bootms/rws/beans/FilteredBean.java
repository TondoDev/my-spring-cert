package org.tondo.bootms.rws.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// we caln list properties, restrict both or only serialization or deserialization
@JsonIgnoreProperties(value = {"value"})
public class FilteredBean {

	private String value;
	private String name;
	
	// will be ignored in serialization
	@JsonIgnore
	private String password;
	
	public FilteredBean(String value, String name, String password) {
		this.value = value;
		this.name = name;
		this.password = password;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
