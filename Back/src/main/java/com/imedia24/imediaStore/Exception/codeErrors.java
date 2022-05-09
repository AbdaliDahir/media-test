package com.imedia24.imediaStore.Exception;

public enum codeErrors {
	
	PRODUCT_NOT_FOUND(001),
	PRODUCT_NOT_VALID(011),

	CATEGORY_NOT_FOUND(002),
	CATEGORY_NOT_VALID(022);

	private int code;

	codeErrors(int code) {
	  this.code = code;
	}

	public int getCode() {
	  return code;
	}
}
