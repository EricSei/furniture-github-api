package com.eric.furniturehubapi.exception;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String path;

	public ErrorDetails(Date date, String message, String path) {
		super();
		this.timestamp = date;
		this.message = message;
		this.path = path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
}
