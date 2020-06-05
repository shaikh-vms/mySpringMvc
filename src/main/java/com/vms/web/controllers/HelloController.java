package com.vms.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	/*
	 * if shows in log then the spring mvc setup is successful
	 * public HelloController() {
	 * 
	 * System.out.println("hello controller instantiated"); }
	 */
	//handler for specific request
	
	@RequestMapping("/hello-mvc")
	public String sayHello() {
		System.out.println("Handler mapping for /hello-mvc(HelloController.sayHello) called");
		return "hello";
	}
}
