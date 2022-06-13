package com.growth.cafe.web.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.reply.Reply;
import com.growth.cafe.service.ReplyService;
import com.growth.cafe.web.dto.ReplySaveRequestDto;
import com.growth.cafe.web.dto.ResponseDto;

@RestController
public class ReplyApiController {
	@Autowired
	private ReplyService rs;

	@PostMapping("/api/replyWrite")
	public ResponseDto<Integer> replyWrite(@RequestBody ReplySaveRequestDto rDto){
		System.out.println("성공");
		
		
		rs.replyWrite(rDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}
