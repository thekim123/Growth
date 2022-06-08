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
	
	@Transactional
	public void upload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getMember());
		imageRepository.save(image);
	}
	
	@Transactional(readOnly = true)
	public Image detailImage(int id) {
		return imageRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
		});
	}
	
	@Transactional
	public void update(int id, Image requestImage) {
		Image image = imageRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다."); 
		});
		image.setTitle(requestImage.getTitle());
		image.setContent(requestImage.getContent());
	}

	@Transactional
	public void delete(int id) {
		imageRepository.deleteById(id);
	}
}
