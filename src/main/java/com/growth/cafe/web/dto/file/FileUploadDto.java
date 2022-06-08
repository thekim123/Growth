package com.growth.cafe.web.dto.file;

import org.springframework.web.multipart.MultipartFile;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.resource.Reference;

import lombok.Data;

@Data
public class FileUploadDto {
	
	private String title;
	private MultipartFile file;
	private String content;
	
	public Reference toEntity(String postFileUrl, Member member) {
		return Reference.builder()
				.title(title)
				.content(content)
				.member(member)
				.postFileUrl(postFileUrl)
				.build();
	}

}
