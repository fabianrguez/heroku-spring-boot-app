package com.example.heroku.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path = "/world")
	public String helloWorld() {
		return "Hello, World!";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/custom/{name}")
	public String helloCustom(@PathVariable String name) {
		return "Hello " + name + "!";
	}
}
