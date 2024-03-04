package com.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	
	public String city;
    public String state;
    public String country;
    @JsonProperty("default") 
    public int mydefault;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getMydefault() {
		return mydefault;
	}
	public void setMydefault(int mydefault) {
		this.mydefault = mydefault;
	}
    
    

}
