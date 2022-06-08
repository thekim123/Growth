package com.growth.cafe.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import com.growth.cafe.domain.image.Image;
import com.growth.cafe.domain.member.Member;

import lombok.Data;

@Data
public class ImageUploadDto {
	private String title;
	private MultipartFile file;
	private String content;
	
	public Image toEntity(String postImageUrl, Member member) {
		return Image.builder()
				.title(title)
				.content(content)
				.member(member)
				.postImageUrl(postImageUrl)
				.build();
	}

}
