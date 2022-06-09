package com.growth.cafe.service;

import java.io.File;
import java.io.IOException;
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
import com.growth.cafe.domain.image.Image;
import com.growth.cafe.domain.image.ImageRepository;
import com.growth.cafe.domain.member.MemberRepository;
import com.growth.cafe.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional(readOnly = true)
	public Page<Image> showImages(Pageable pageable){
		return imageRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Image detailImage(int id) {
		return imageRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}
	
	public String generateFileName(ImageUploadDto imageUploadDto) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		return imageFileName;
	}
	
	public Path generateFilePath(String imageFileName) {
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		return imageFilePath;
	}
	
	@Transactional
	public void upload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		
		//Dto 만들면 좋을듯
		String imageFileName = generateFileName(imageUploadDto);
		Path imageFilePath = generateFilePath(imageFileName);
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getMember());
		imageRepository.save(image);
	}
	
	@Transactional
	public void update(int id, ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		String imageFileName = generateFileName(imageUploadDto);
		Path imageFilePath = generateFilePath(imageFileName);
		
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다."); 
		});
		
		try {
			Files.delete(generateFilePath(image.getPostImageUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		image.setTitle(imageUploadDto.getTitle());
		image.setContent(imageUploadDto.getContent());
		image.setPostImageUrl(imageFileName);
	}

	@Transactional
	public void delete(int id) {
		Image image = imageRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
		try {
			Files.delete(generateFilePath(image.getPostImageUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageRepository.deleteById(id);
	}
}
