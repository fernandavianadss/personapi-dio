package com.digitalinovationone.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	@GetMapping
	public String helloMessage() {
		return "Hello, Digital innovation one";
	}
}
