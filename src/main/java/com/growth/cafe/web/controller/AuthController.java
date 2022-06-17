package com.growth.cafe.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.handler.CheckUsernameValid;
import com.growth.cafe.service.AuthService;
import com.growth.cafe.web.dto.auth.SignupDto;

@Controller
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private CheckUsernameValid checkUsernameValidator;
	
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
    }
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult, Errors errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("signupDto", signupDto);
			
			Map<String, String> validatorResult = authService.validateHandling(errors);
			for(String key:validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "auth/signup";
		}
		
		Member member = signupDto.toEntity();
		authService.join(member);
		return "auth/signin";
	}
}
