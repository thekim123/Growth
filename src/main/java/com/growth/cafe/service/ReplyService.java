package com.growth.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.domain.reply.Reply;
import com.growth.cafe.domain.reply.ReplyRepository;
import com.growth.cafe.domain.sns.Sns;
import com.growth.cafe.domain.sns.SnsRepository;
import com.kim.blog.model.UserPeter;
import com.kim.blog.model.board;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository rr;
	
	@Autowired
	private SnsRepository sr;
	
	@Autowired
	private MemberRepository mr;
	
	@Transactional
	public void replyWrite(Reply r) {
		Member member = mr.findById(r.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다 ");
		});
		
		
		Sns sns = sr.findById(r.getSnsId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글쓰기 실패 : 해당 게시글이 없습니다");
		});
		Reply r = new Reply();
		r.update(member, sns, r.getContent());
		rr.save(r);
	}

	
}
