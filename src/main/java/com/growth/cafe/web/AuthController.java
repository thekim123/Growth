package com.growth.cafe.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.handler.ex.CustomValidationException;
import com.growth.cafe.service.AuthService;
import com.growth.cafe.web.dto.auth.SignupDto;

@Controller
public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		Member member = signupDto.toEntity();
		authService.회원가입(member);
		return "auth/signin";
	}
}
