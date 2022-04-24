package com.example.demo;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class customer {
	@GetMapping("/output")
	public String say(@RequestParam String expression){
		
		try {
		String myPostfix=infixToPostfix.postfix(expression);
		String result=evaluatePostfix.evaluate_postfix(myPostfix);
		
		return result;
		}catch(Exception e) {
    		return "E";
    	}
	}
	
}
