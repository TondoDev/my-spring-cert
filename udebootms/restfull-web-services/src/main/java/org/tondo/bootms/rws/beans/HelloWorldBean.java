package org.tondo.bootms.rws.beans;

public class HelloWorldBean {

	private String message;
	
	public HelloWorldBean(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("HelloWorldBean [message = %s]", this.message);
	}
}
