package com.pce.BookMeTutor.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class MainController {

	@GetMapping("/")
	@CrossOrigin
	@ResponseBody
	public String hello() {
		return "<h2>Welcome to Bookmetutor Backend</h2>";
	}

}
