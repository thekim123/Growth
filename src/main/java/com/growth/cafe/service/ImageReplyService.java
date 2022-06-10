package com.growth.cafe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.domain.image.Image;
import com.growth.cafe.domain.image.ImageReply;
import com.growth.cafe.domain.image.ImageReplyRepository;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageReplyService {
	
	private final ImageReplyRepository imageReplyRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public ImageReply writeImageReply(String content, int imageId, int memberId) {
		Image image  = new Image();
		image.setId(imageId);
		
		Member memberEntity = memberRepository.findById(memberId).orElseThrow(()->{
			throw new CustomApiException("유저를 찾을 수 없습니다.");
		});
		memberEntity.setId(memberId);
		
		ImageReply reply = new ImageReply();
		reply.setImage(image);
		reply.setContent(content);
		reply.setMember(memberEntity);
		
		return imageReplyRepository.save(reply);
	}

	@Transactional
	public void delete(int id) {
		try {
			imageReplyRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
	}
	
}
