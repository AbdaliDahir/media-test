package com.imedia24.imediaStore.Exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {
	
	@Getter
	private codeErrors codeError;

	public NotFoundException(String message) {
	  super(message);
	}
	
	public NotFoundException(String message, codeErrors codeError) {
	  super(message);
	  this.codeError = codeError;
	}

	public NotFoundException(String message, Throwable cause) {
	  super(message, cause);
	}

	public NotFoundException(String message, Throwable cause, codeErrors codeError) {
	  super(message, cause);
	  this.codeError = codeError;
	}

	

}
