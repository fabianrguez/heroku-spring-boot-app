package com.example.heroku.controller;

import com.example.heroku.model.Greeting;
import com.example.heroku.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws InterruptedException {
		Thread.sleep(1000);
		return new Greeting("Hello, " + message.getName() + "!");
	}
}
