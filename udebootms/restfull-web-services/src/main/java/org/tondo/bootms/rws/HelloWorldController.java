package org.tondo.bootms.rws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tondo.bootms.rws.beans.HelloWorldBean;

// Controller + ResponseBody on each method
@RestController
public class HelloWorldController {

	
	@GetMapping( path = "/hello-world")
	public String helloWorld( ) {
		return "Hello World";
	}
	
	// automatically converted to JSON
	// boot has on classpath Jackson
	@GetMapping( path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Wello world from Bean!");
	}
	
	@GetMapping( path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
}
