package org.tondo.bootms.rws.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bobo");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Janko", "Hrasko"));
	}
	
	
	@GetMapping(value = "/person", params = "version=1")
	public PersonV1 personV1byParam() {
		return new PersonV1("Bobo");
	}
	
	@GetMapping(value = "/person", params = "version=2")
	public PersonV2 personV2byParam() {
		return new PersonV2(new Name("Janko", "Hrasko"));
	}
	
	

	@GetMapping(value = "/person", headers = "X-API-VERSION=1")
	public PersonV1 personV1byHeader() {
		return new PersonV1("Bobo");
	}
	
	@GetMapping(value = "/person", headers = "X-API-VERSION=2")
	public PersonV2 personV2byHeader() {
		return new PersonV2(new Name("Janko", "Hrasko"));
	}
	
	// this one come into Accept header of the request 
	@GetMapping(value = "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 personV1byProduces() {
		return new PersonV1("Bobo");
	}
	
	@GetMapping(value = "/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 personV2byProduces() {
		return new PersonV2(new Name("Janko", "Hrasko"));
	}
}


