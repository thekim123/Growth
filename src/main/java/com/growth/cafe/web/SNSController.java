package com.growth.cafe.web;

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
	
	@Autowired
	private SnsService ss;
	

	@GetMapping({"/", "/sns/story"})
	public String story(Model model, @PageableDefault(size=3, sort = "id") Pageable p) {
		model.addAttribute("snsList", ss.SnsSelect(p));
		return "/sns/story";
	}
	
	@GetMapping("/snswrite")
	public String snsWrite() {
		
		
		return "sns/SnsWrite";
	}
	@GetMapping("/sns/{id}")
	public String snsWrite(@PathVariable int id, Model model) {
		
		model.addAttribute("snsDetail", ss.SnsDetail(id));
		
		
		
		return "sns/SnsDetail";
	}
	@GetMapping("/snsUpdate/{id}")
	public String snsUpdate(@PathVariable int id, Model model) {
		model.addAttribute("snsUpdate", ss.SnsDetail(id));
		
		return "sns/SnsUpdate";
	}
	
}
