package com.growth.cafe.web.api;

import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.service.ReferenceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReferenceApiController {
	
	private final ReferenceService fileService;

}
