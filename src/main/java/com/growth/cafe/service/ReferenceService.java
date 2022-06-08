package com.growth.cafe.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.resource.Reference;
import com.growth.cafe.domain.resource.ReferenceRepository;
import com.growth.cafe.web.dto.file.FileUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReferenceService {
	
	private final ReferenceRepository fileRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	

	@Transactional(readOnly = true)
	public Page<Reference> showFiles(Pageable pageable) {
		return fileRepository.findAll(pageable);
	}
	
	@Transactional
	public void upload(FileUploadDto fileUploadDto, PrincipalDetails principalDetails) {

		UUID uuid = UUID.randomUUID();
		String fileName = uuid+"_"+fileUploadDto.getFile().getOriginalFilename();
		
		Path filePath = Paths.get(uploadFolder+fileName);
		try {
			Files.write(filePath, fileUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Reference file = fileUploadDto.toEntity(fileName, principalDetails.getMember());
		fileRepository.save(file);
	}
	
	
}
