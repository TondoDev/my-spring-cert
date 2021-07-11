package org.tondo.bootms.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tondo.bootms.exchange.data.CurrencyExchange;
import org.tondo.bootms.exchange.data.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String  from, @PathVariable String to) {
		//CurrencyExchange retval = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(25.5));
		CurrencyExchange retval = repository.findByFromAndTo(from, to);
		
		if (retval == null) {
			throw new RuntimeException("Unable to find data for '" + from + "' and '" + to + "'!");
		}
				
		String port = environment.getProperty("local.server.port");
		retval.setEnvironment(port);
		return retval;
	}
}
