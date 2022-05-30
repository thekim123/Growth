package com.growth.cafe.web.dto.auth;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.growth.cafe.domain.member.Member;

import lombok.Data;

@Data
public class SignupDto {
	
	@Size(min = 5, max = 20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public Member toEntity() {
		return Member.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
