package com.growth.cafe.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
}
