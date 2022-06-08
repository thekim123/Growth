package com.growth.cafe.web.dto.member;

import com.growth.cafe.domain.member.Member;

import lombok.Data;

@Data
public class MemberUpdateDto {
	private String name;
	private String password;
	private String email;
	
	public Member toEntity() {
		return Member.builder()
				.name(name)
				.password(password)
				.email(email)
				.build();
	}
}
