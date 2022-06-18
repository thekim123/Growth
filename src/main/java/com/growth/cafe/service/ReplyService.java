package com.growth.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.domain.reply.Reply;
import com.growth.cafe.domain.reply.ReplyRepository;
import com.growth.cafe.domain.sns.Sns;
import com.growth.cafe.handler.ex.CustomApiException;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional
	public Reply replyWrite(String content, int snsId, int memberId) {
		Sns sns = new Sns();
		sns.setId(snsId);
		System.out.println(memberId);
		Member memberEntity = memberRepository.findById(memberId).orElseThrow(()->{
			throw new CustomApiException("유저를 찾을 수 없습니다.");
		});
		memberEntity.getId();
		memberEntity.getUsername();
		
		memberEntity.setId(memberId);
		
		Reply reply = new Reply();
		reply.setContent(content);
		reply.setSns(sns);
		reply.setMember(memberEntity);
		
		return replyRepository.save(reply);
	}

	
}
