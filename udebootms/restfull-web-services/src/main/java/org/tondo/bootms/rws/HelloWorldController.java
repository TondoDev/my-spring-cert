package org.tondo.bootms.rws;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.tondo.bootms.rws.beans.HelloWorldBean;

// Controller + ResponseBody on each method
@RestController
public class HelloWorldController {
	
	private static final String MORNING_MSG = "good.morning.message";

	@Autowired
	private MessageSource messageSource;
	
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
	
	@GetMapping("/hello-i18n")
	public String helloInter(@RequestHeader(name="Accept-Language", required = false) String locale) {
		// Locale parameter fails, when browser sends something more complicated like:
		// sk-SK,sk;q=0.9,cs;q=0.8,en-US;q=0.7,en;q=0.6
		// LocaleContextHolder - contains correct value according to priority
		return messageSource.getMessage(MORNING_MSG, null, LocaleContextHolder.getLocale());
	}
}
