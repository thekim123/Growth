package com.growth.cafe.web.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.service.MemberService;
import com.growth.cafe.web.dto.CMRespDto;
import com.growth.cafe.web.dto.member.MemberUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

	private final MemberService memberService;
	
	@PutMapping("/api/member/{id}")
	public CMRespDto<?> update(
			@PathVariable int id,
			MemberUpdateDto userUpdateDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails
			){
		Member memberEntity = memberService.modifyMember(id, userUpdateDto.toEntity());
		
		return new CMRespDto<>(1, "회원수정 완료", memberEntity);
	}
}
