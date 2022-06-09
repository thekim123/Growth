package com.growth.cafe.web;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.image.Image;
import com.growth.cafe.handler.ex.CustomValidationException;
import com.growth.cafe.service.ImageService;
import com.growth.cafe.web.dto.ResponseDto;
import com.growth.cafe.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	
	private final ImageService imageService;
	
	@GetMapping("/image")
	public String imageBoard(Model model, @PageableDefault Pageable pageable) {
		model.addAttribute("images", imageService.showImages(pageable));
		return "image/image";
	}
	
	@GetMapping("/image/{id}")
	public String imageDetail(@PathVariable int id, Model model) {
		model.addAttribute("image", imageService.detailImage(id));
		return "image/detail";
	}
	
	@GetMapping("/image/upload")
	public String upload() {
		return "image/upload";
	}
	
	@PostMapping("/image/upload")
	public String save(@Valid ImageUploadDto imageUploadDto, BindingResult bindingResult,@AuthenticationPrincipal PrincipalDetails principalDetails){
		
		if(imageUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
		}
		imageService.upload(imageUploadDto, principalDetails);
		return "redirect:/image";
	}
	
}
