package com.growth.cafe.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.domain.resource.FIleReplyRepository;
import com.growth.cafe.domain.resource.FileReply;
import com.growth.cafe.domain.resource.Reference;
import com.growth.cafe.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileReplyService {
	
	private final FIleReplyRepository fIleReplyRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public FileReply writeFileReply(String content, Integer fileId, int memberId) {
		Reference file = new Reference();
		file.setId(fileId);
		
		Member memberEntity = memberRepository.findById(memberId).orElseThrow(()->{
			throw new CustomApiException("유저를 찾을 수 없습니다");
		});
		memberEntity.setId(memberId);
		
		FileReply reply = new FileReply();
		reply.setContent(content);
		reply.setMember(memberEntity);
		reply.setFile(file);
		
		return fIleReplyRepository.save(reply);
	}
	
	@Transactional
	public void delete(int id) {
		try {
			fIleReplyRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomApiException(e.getMessage());
		}
	}
	
}
