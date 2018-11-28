package com.mohit.app.ws.ui.model.response;

public enum ErrorMessages {

	RECORD_ALREADY_EXISTS("Record already exists");
	
	private String errorMessage;
	
	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
