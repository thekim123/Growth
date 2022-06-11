package com.growth.cafe.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.sns.Sns;
import com.growth.cafe.service.SnsService;
import com.growth.cafe.web.dto.CMRespDto;
import com.growth.cafe.web.dto.ResponseDto;

@RestController
public class SnsApiController {
	
	@Autowired
	private SnsService ss;
	
	@GetMapping({"/api/sns"})
	public ResponseEntity<?> snsLoad(Model model, @PageableDefault(size=5, sort = "id") Pageable p) {
		Page<Sns> snsList = ss.SnsSelect(p);
		return new ResponseEntity<>(new CMRespDto<>(1, "성공", snsList), HttpStatus.OK);
	}

	@PostMapping("/api/snsWrite")
	public ResponseDto<Integer> snsWrite(@RequestBody Sns s, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		ss.SnsWrite(s, principalDetails.getMember());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@DeleteMapping("/api/snsDelete/{id}") // api/snsDelete
	public ResponseDto<Integer> snsDelete(@PathVariable int id){
		ss.SnsDelete(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/snsUpdate/{id}")
	public ResponseDto<Integer> snsUpdate(@PathVariable int id, @RequestBody Sns s){
		ss.SnsUpdate(id,s);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}
