package com.k0r4nd.wimo.api.model.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String msg){
		super(msg);
	}

}
