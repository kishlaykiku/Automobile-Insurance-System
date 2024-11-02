package com.hexavarsity.ais;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class HelloController {
    
    @RequestMapping(value = "/" , method = RequestMethod.GET)
	public static String home() {

		return "Everyone says 'Hello, World!', but no one asks 'How is world?' :(";
	}

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {

        return "Hello, World!";
    }
}