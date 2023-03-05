package com.ada.ticketsaleapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VooEsgotadoException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public VooEsgotadoException(String msg) {
		super(msg);
	}

}
