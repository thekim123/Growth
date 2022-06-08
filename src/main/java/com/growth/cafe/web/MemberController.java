package com.growth.cafe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.growth.cafe.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {

	public final MemberService memberService;
	
	@GetMapping("/member/update")
	public String updateForm() {
		return "member/update";
	}
}
