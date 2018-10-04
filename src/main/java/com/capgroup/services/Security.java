package com.capgroup.services;

public class Security {

	private String name;
	private String cusip;
	private float currentPrice;
	private float previousClose;
	private float todayVolume;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}
	public float getPreviousClose() {
		return previousClose;
	}
	public void setPreviousClose(float previousClose) {
		this.previousClose = previousClose;
	}
	public float getTodayVolume() {
		return todayVolume;
	}
	public void setTodayVolume(float todayVolume) {
		this.todayVolume = todayVolume;
	}
	
	
}
