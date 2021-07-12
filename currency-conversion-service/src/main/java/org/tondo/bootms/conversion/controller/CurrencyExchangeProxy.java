package org.tondo.bootms.conversion.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name is application name of service
//@FeignClient(name="currency-exchange", url="localhost:8000")

// omitting url will result that naming server provides the correct address according to name
@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

	// more or less the copy paste of the prototype of controller method of currency exchange service
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
