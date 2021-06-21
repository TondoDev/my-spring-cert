package org.tondo.bootms.rws.versioning;

public class PersonV1 {

	private String name;
	
	
	public PersonV1 (String initName) {
		this.name = initName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
