package org.tondo.bootms.limit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tondo.bootms.limit.Configuration;
import org.tondo.bootms.limit.bean.Limits;

@RestController
public class LimitsController {

	
	// our class read from property file
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(configuration.getMinimum(), this.configuration.getMaximum());
	}
}
