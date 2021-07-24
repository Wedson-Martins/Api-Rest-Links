package com.wmdm.linksforyoutube.exceptions;

public class OperationNotPerformedException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Operation not performed";
	}

}
