package com.menu.dto;

public class ResponseDTO {
	
	StatusCode status;
	String message;
	public StatusCode getStatus() {
		return status;
	}
	public void setStatus(StatusCode status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
