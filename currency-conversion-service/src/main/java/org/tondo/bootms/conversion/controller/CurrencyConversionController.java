package org.tondo.bootms.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	// feign client
	@Autowired
	private CurrencyExchangeProxy exchangeProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		
		// user to resolve placeholders in URI passed to RestTemplate
		Map<String, String> parameters = new HashMap<>();
		parameters.put("from", from);
		parameters.put("to", to);
		

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				// We rely on CurrencyExchange serialized JSON contains attributes with the same name
				// as CurrencyConversion object
				CurrencyConversion.class,  
				parameters);
		
		
		CurrencyConversion cc = responseEntity.getBody();
		
		CurrencyConversion retval = new CurrencyConversion(
				cc.getId(), 
				cc.getFrom(), 
				cc.getTo(), 
				quantity, 
				cc.getConversionMultiple(), 
				quantity.multiply(cc.getConversionMultiple()), 
				cc.getEnvironment());
		return retval;
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversion cc = exchangeProxy.retrieveExchangeValue(from, to);
		CurrencyConversion retval = new CurrencyConversion(
				cc.getId(), 
				cc.getFrom(), 
				cc.getTo(), 
				quantity, 
				cc.getConversionMultiple(), 
				quantity.multiply(cc.getConversionMultiple()), 
				cc.getEnvironment() + "_feign");
		return retval;
	}
}
