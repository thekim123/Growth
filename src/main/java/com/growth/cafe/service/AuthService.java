package com.growth.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;

@Service
public class AuthService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public Member 회원가입(Member member) {
		String rawPassword = member.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		member.setPassword(encPassword);
		member.setRole("USER");
		
		Member memberEntity = memberRepository.save(member);
		return memberEntity;
		//userEntity - DB에서 가져온 User
		//user - html에서 가져온 User
	}

}
