package com.growth.cafe.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public Member modifyMember(int id, Member member) {
		Member memberEntity = memberRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		String rawPassword  = member.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		memberEntity.setPassword(encPassword);
		memberEntity.setName(member.getName());
		memberEntity.setEmail(member.getEmail());
		
		return memberEntity;
	}
	
	@Transactional
	public Member updateProfileImage(int principalId, MultipartFile profileImageUrl, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+profileImageUrl.getOriginalFilename();
		Path imageFilepath = Paths.get(uploadFolder+imageFileName);
		System.out.println(imageFileName);
		try {
			Files.write(imageFilepath, profileImageUrl.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Member memberEntity = memberRepository.findById(principalId).orElseThrow(()->{
			throw new CustomApiException("id를 찾을 수 없습니다");
		});
		memberEntity.setProfileImageUrl(imageFileName);
		principalDetails.getMember().setProfileImageUrl(imageFileName);
		return memberEntity;
	}

	@Transactional(readOnly = true)
	public boolean checkUsernameDuplicate(String username) {
		return memberRepository.existsByUsername(username);
	}
}
