package com.vms.service;

public class GreetingService {

	public String getMessage(String name) {
		if(name ==null)
			name = "web mvc";
		return "Hello,"+name+"!";
	}

}
