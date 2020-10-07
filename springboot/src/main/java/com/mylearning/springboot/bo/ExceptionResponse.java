package com.mylearning.springboot.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String detail;

	@Override
	public String toString() {
		return "ExceptionResponse [timestamp=" + timestamp + ", message=" + message + ", detail=" + detail + "]";
	}

}
