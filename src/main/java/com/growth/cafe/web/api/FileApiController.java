package com.growth.cafe.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.domain.video.FileInfo;
import com.growth.cafe.service.FileService;

@RestController
//@RequestMapping("/")
public class FileApiController {

	@Autowired
	private FileService fileService;

//	http://localhost:8081/download/0107DB.png
	@GetMapping("/file/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {

		Resource file = fileService.getResource(fileService.decodeFile(filename));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + filename + "\"").body(file);
	}
	
//	@GetMapping("/jsonfiles")
//	public Page<FileInfo> getListFiles(Model model,
//			@PageableDefault(size=10, sort="fileanme", direction=Sort.Direction.DESC) Pageable pageable) {
//		return fileService.toPage(pageable);
//	}

}
