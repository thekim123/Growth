package com.growth.cafe.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.growth.cafe.handler.ex.CustomApiException;
import com.growth.cafe.handler.ex.CustomException;
import com.growth.cafe.handler.ex.CustomValidationApiException;
import com.growth.cafe.handler.ex.CustomValidationException;
import com.growth.cafe.util.Script;
import com.growth.cafe.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(CustomException.class)
	public String exception(CustomException e) {
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e){
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<?> apiException(CustomApiException e){
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	
}
