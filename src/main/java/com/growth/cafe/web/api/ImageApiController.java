package com.growth.cafe.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.image.Image;
import com.growth.cafe.service.ImageService;
import com.growth.cafe.web.dto.CMRespDto;
import com.growth.cafe.web.dto.ResponseDto;
import com.growth.cafe.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

	private final ImageService imageService;
	
	@DeleteMapping("/api/image/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		imageService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
