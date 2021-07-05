package org.tondo.bootms.limit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// prefix of configuration entries, rest will be resolved by field names
//limits-service.minimum=5
//limits-service.maximum=900
@Component // makes spring bean from this
@ConfigurationProperties("limits-service")
public class Configuration {

	private int minimum;
	private int maximum;
	
	
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	
}
