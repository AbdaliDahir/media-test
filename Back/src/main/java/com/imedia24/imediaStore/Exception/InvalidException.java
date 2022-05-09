package com.imedia24.imediaStore.Exception;

import java.util.List;

import lombok.Getter;

public class InvalidException extends RuntimeException {
	
	@Getter
	private codeErrors codeError;
	
	@Getter
	private List<String> messages;

	  public InvalidException(String message) {
	    super(message);
	  }

	  public InvalidException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  public InvalidException(String message, Throwable cause, codeErrors codeError) {
	    super(message, cause);
	    this.codeError = codeError;
	  }

	  public InvalidException(String message, codeErrors codeError) {
	    super(message);
	    this.codeError = codeError;
	  }

	  public InvalidException(String message, codeErrors codeError, List<String> messages) {
	    super(message);
	    this.codeError = codeError;
	    this.messages = messages;
	  }

}
