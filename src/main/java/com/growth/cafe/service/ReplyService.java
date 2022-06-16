package com.growth.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.domain.reply.Reply;
import com.growth.cafe.domain.reply.ReplyRepository;
import com.growth.cafe.domain.sns.Sns;
import com.growth.cafe.domain.sns.SnsRepository;
import com.growth.cafe.web.dto.ReplySaveRequestDto;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository rr;
	
	@Autowired
	private MemberRepository mr;
	
	@Autowired
	private SnsRepository sr;
	
	@Transactional
	public void replyWrite(ReplySaveRequestDto r) {
		
		Member m = mr.findById(r.getMemberId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다 ");
		});
		
		Sns s = sr.findById(r.getSnsId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기 실패 : 해당 게시글이 없습니다");
		});
		Reply reply = new Reply();
		System.out.println("r.getContent()");
		reply.update(m, s, r.getContent());
		
		
		rr.save(reply);
	}
	@Transactional
	public void replyDelete(int id) {
		rr.deleteById(id);
	}
	
}
