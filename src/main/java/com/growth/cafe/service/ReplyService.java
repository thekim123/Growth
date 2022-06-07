package com.growth.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.reply.Reply;
import com.growth.cafe.domain.reply.ReplyRepository;
import com.growth.cafe.domain.sns.Sns;

@Service
public class ReplyService {

	@Autowired
	private ReplyRepository rr;
	
	@Transactional
	public void replyWrite(Reply r) {
		System.out.println(r.getContent());
		System.out.println(r.getMemberId());
		System.out.println(r.getSnsId());
		rr.save(r);
	}

	
}
