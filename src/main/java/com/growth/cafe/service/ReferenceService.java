package com.growth.cafe.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.resource.Reference;
import com.growth.cafe.domain.resource.ReferenceRepository;
import com.growth.cafe.web.dto.file.FileUploadDto;
import com.growth.cafe.web.dto.image.ImageUploadDto;

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
	
	public String generateFileName(FileUploadDto fileUploadDto) {
		UUID uuid = UUID.randomUUID();
		String fileFileName = uuid+"_"+fileUploadDto.getFile().getOriginalFilename();
		return fileFileName;
	}
	
	public Path generateFilePath(String fileName) {
		Path filePath = Paths.get(uploadFolder+fileName);
		return filePath;
	}
	
	@Transactional
	public void upload(FileUploadDto fileUploadDto, PrincipalDetails principalDetails) {

		String fileName = generateFileName(fileUploadDto);
		Path filePath = generateFilePath(fileName);
		
		try {
			Files.write(filePath, fileUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Reference file = fileUploadDto.toEntity(fileName, principalDetails.getMember());
		fileRepository.save(file);
	}

	@Transactional(readOnly = true)
	public Reference detailFiles(int id) {
			
		return fileRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void update(int id, @Valid FileUploadDto fileUploadDto, PrincipalDetails principalDetails) {
		String fileName = generateFileName(fileUploadDto);
		Path filePath = generateFilePath(fileName);
		
		try {
			Files.write(filePath, fileUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Reference reference = fileRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
		});
		
		try {
			Files.delete(generateFilePath(reference.getPostFileUrl()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reference.setTitle(fileUploadDto.getTitle());
		reference.setContent(fileUploadDto.getContent());
		reference.setPostFileUrl(fileName);
		
	}

	@Transactional
	public void delete(int id) {
		Reference reference = fileRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
		try {
			Files.delete(generateFilePath(reference.getPostFileUrl()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileRepository.deleteById(id);
	}
	
}
