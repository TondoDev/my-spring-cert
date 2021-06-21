package org.tondo.bootms.rws.versioning;

public class PersonV2 {

	private Name name;

	public PersonV2(Name initName) {
		this.name = initName;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
}
