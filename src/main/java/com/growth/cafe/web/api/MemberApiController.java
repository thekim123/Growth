package com.growth.cafe.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.handler.ex.CustomApiException;
import com.growth.cafe.handler.ex.CustomException;
import com.growth.cafe.service.MemberService;
import com.growth.cafe.web.dto.CMRespDto;
import com.growth.cafe.web.dto.ResponseDto;
import com.growth.cafe.web.dto.auth.SignupDto;
import com.growth.cafe.web.dto.member.MemberUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

	private final MemberService memberService;

	@ResponseBody
	@PostMapping("/exists")
	public ResponseEntity<?> checkUsernameDuplicate(@RequestBody SignupDto dto) {
		String username = dto.getUsername();
		System.out.println(username);
		boolean isDuplicate = memberService.checkUsernameDuplicate(username);
		return new ResponseEntity<>(new CMRespDto<>(1, "사용가능한 아이디입니다.", isDuplicate), HttpStatus.OK);
	}

	@PutMapping("/api/member/{principalId}/profileImageUrl")
	public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		memberService.updateProfileImage(principalId, profileImageFile, principalDetails);
		return new ResponseEntity<>(new CMRespDto<>(1, "프로필사진 수정 완료", null), HttpStatus.OK);
	}

	@PutMapping("/api/member/{id}")
	public CMRespDto<?> update(@PathVariable int id, MemberUpdateDto userUpdateDto,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		Member memberEntity = memberService.modifyMember(id, userUpdateDto.toEntity());

		return new CMRespDto<>(1, "회원수정 완료", memberEntity);
	}
}
