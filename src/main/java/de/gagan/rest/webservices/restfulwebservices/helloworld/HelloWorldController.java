package de.gagan.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/hello-world")
	public String getHelloWorld() {
		return "Hello World!!!";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World!!!");
	}
	
	@GetMapping("/hello-world-bean/{message}")
	public HelloWorldBean getHelloWorldBeanMessage(@PathVariable String message) {
		return new HelloWorldBean("Hello " + message);
	}
	
	@GetMapping("/hello-world-inter-bean")
	public HelloWorldBean getHelloWorldInterBean() {
		String message = messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
		return new HelloWorldBean(message);
	}
}
