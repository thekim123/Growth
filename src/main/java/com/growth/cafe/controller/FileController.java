package com.growth.cafe.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.growth.cafe.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	@GetMapping("/file/list")
	public String getListFiles(Model model, @PageableDefault(size=10) Pageable pageable) {
		model.addAttribute("filelist", fileService.toPage(pageable));
		return "video/filelist";
	}
}
