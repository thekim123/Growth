package com.growth.cafe.handler;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckUsernameValid extends AbstractValidator<SignupDto> {

	private final MemberRepository memberRepository;

	@Override
	protected void doValidate(SignupDto dto, Errors errors) {
		if(memberRepository.existsByUsername(dto.toEntity().getUsername())) {
			errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
		}
	}
	
	
	
}
