package com.growth.cafe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SNSController {

	@GetMapping({"/", "/sns/story"})
	public String story() {
		return "/sns/story";
	}
}
