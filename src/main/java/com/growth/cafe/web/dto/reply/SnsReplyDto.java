package com.growth.cafe.web.dto.reply;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SnsReplyDto {

	@NotBlank
	private String content;
	@NotNull
	private Integer snsId;
	
}