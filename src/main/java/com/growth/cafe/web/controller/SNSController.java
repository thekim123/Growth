package com.growth.cafe.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.growth.cafe.service.SnsService;

@Controller
public class SNSController {

	@GetMapping({"/", "/sns"})
	public String story() {
		return "sns/sns";
	}
	
	@GetMapping("/snswrite")
	public String snsWrite() {
		
		
		return "sns/SnsWrite";
	}
	
}
