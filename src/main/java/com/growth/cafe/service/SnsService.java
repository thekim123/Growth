package com.growth.cafe.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.sns.Sns;
import com.growth.cafe.domain.sns.SnsRepository;



@Service
public class SnsService {
	@Autowired
	private SnsRepository snsRepository;
	
	@Transactional
	public void SnsWrite(Sns s, Member member) {
		s.setMember(member);
		snsRepository.save(s);
	}
	
	@Transactional(readOnly = true)
	public Page<Sns> SnsSelect(Pageable pageable) {
		Page<Sns> snses = snsRepository.findAll(pageable);
		return snses;
	}
		
	@Transactional
	public void SnsDelete(int id) {
		snsRepository.deleteById(id);
	}
	@Transactional
	public void SnsUpdate(int id, Sns requestSns) {
		Sns updateSns = snsRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글수정하기 실패 : 해당 아이디를 찾을수 없습니다");
		});
		updateSns.setTitle(requestSns.getTitle());
		updateSns.setContent(requestSns.getContent ());
	}
}
