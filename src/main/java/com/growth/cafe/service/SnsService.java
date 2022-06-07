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
	private SnsRepository sr;
	
	@Transactional
	public void SnsWrite(Sns s, Member member) {
		s.setMember(member);
		sr.save(s);
	}
	
	@Transactional(readOnly = true)
	public Page<Sns> SnsSelect(Pageable p) {
		
		return sr.findAll(p);
	}
	
	@Transactional(readOnly = true)
	public Sns SnsDetail(int id) {
		return sr.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글상세보기 실패 : 아이디를 찾을수 없습니다");
		});
		
	}
	@Transactional
	public void SnsDelete(int id) {
		
		sr.deleteById(id);
	}
	@Transactional
	public void SnsUpdate(int id, Sns requestSns) {
		Sns updateSns = sr.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글수정하기 실패 : 해당 아이디를 찾을수 없습니다");
		});
		updateSns.setTitle(requestSns.getTitle());
		updateSns.setContent(requestSns.getContent ());
	}
}
