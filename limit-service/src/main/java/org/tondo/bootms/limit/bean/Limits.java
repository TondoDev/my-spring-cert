package org.tondo.bootms.limit.bean;

public class Limits {

	private int mininum;
	private int maximum;
	
	
	
	public Limits(int mininum, int maximum) {
		this.mininum = mininum;
		this.maximum = maximum;
	}
	
	
	public int getMininum() {
		return mininum;
	}
	public void setMininum(int mininum) {
		this.mininum = mininum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
}
