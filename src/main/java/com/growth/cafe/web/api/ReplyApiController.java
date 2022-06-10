package com.growth.cafe.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.image.ImageReply;
import com.growth.cafe.domain.reply.Reply;
import com.growth.cafe.domain.resource.FileReply;
import com.growth.cafe.service.FileReplyService;
import com.growth.cafe.service.ImageReplyService;
import com.growth.cafe.service.ReplyService;
import com.growth.cafe.web.dto.CMRespDto;
import com.growth.cafe.web.dto.ResponseDto;
import com.growth.cafe.web.dto.reply.ImageReplyDto;
import com.growth.cafe.web.dto.reply.fileReplyDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
	
	private final ReplyService rs;
	private final ImageReplyService imageReplyService;
	private final FileReplyService fileReplyService;

	@PostMapping("/api/replyWrite")
	public ResponseDto<Integer> replyWrite(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		System.out.println("성공");
		Reply r = new Reply();
		r.getContent();
		r.getMemberId();
		rs.replyWrite(r);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/img/reply")
	public ResponseEntity<?>imageReplyWrite(@Valid @RequestBody ImageReplyDto replyDto, 
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		ImageReply imageReply = 
				imageReplyService.writeImageReply(replyDto.getContent(), replyDto.getImageId(), principalDetails.getMember().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기 성공", imageReply), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/img/reply/{id}")
	public ResponseEntity<?> imageReplyDelete(@PathVariable int id){
		imageReplyService.delete(id);
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 삭제 완료",null), HttpStatus.OK);
	}
	
	@PostMapping("/api/file/reply")
	public ResponseEntity<?>fileReplyWrite(@Valid @RequestBody fileReplyDto replyDto, 
			BindingResult bindingResult, 
			@AuthenticationPrincipal PrincipalDetails principalDetails){
		FileReply fileReply = 
				fileReplyService.writeFileReply(replyDto.getContent(), replyDto.getFileId(), principalDetails.getMember().getId());
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기 성공", fileReply), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/api/file/reply/{id}")
	public ResponseEntity<?> fileReplyDelete(@PathVariable int id){
		fileReplyService.delete(id);
		return new ResponseEntity<>(new CMRespDto<>(1, "댓글 삭제 완료",null), HttpStatus.OK);
	}
}
