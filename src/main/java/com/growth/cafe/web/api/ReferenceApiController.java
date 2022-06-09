package com.growth.cafe.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.service.ReferenceService;
import com.growth.cafe.web.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReferenceApiController {
	
	private final ReferenceService fileService;
	
	@DeleteMapping("/api/refer/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		fileService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
