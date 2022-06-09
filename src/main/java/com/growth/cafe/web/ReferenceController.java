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

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.handler.ex.CustomValidationException;
import com.growth.cafe.service.ReferenceService;
import com.growth.cafe.web.dto.file.FileUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReferenceController {

	private final ReferenceService fileService;
	
	@GetMapping("/file")
	public String fileBoard(Model model, @PageableDefault Pageable pageable) {
		model.addAttribute("files", fileService.showFiles(pageable));
		return "file/file";
	}
	
	@GetMapping("/file/{id}")
	public String fileDetail(Model model, @PathVariable int id) {
		model.addAttribute("file", fileService.detailFiles(id));
		return "file/detail";
	}
	
	@GetMapping("/file/upload")
	public String upload() {
		return "file/upload";
	}
	
	@PostMapping("/file/upload")
	public String save(@Valid FileUploadDto fileUploadDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {

		if (fileUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("파일이 첨부되지 않았습니다.", null);
		}
		fileService.upload(fileUploadDto, principalDetails);
		return "redirect:/file";
	}
	
	@GetMapping("/file/{id}/updateForm")
	public String update(@PathVariable int id, Model model) {
		model.addAttribute("file", fileService.detailFiles(id));
		return "file/update";
	}
	
	@PostMapping("/file/{id}/update")
	public String update(@PathVariable int id, @Valid FileUploadDto fileUploadDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if (fileUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("파일이 첨부되지 않았습니다.", null);
		}
		fileService.update(id, fileUploadDto, principalDetails);
		return "redirect:/file";
	}
	
}
