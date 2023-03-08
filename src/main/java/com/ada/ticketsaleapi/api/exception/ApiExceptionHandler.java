package com.ada.ticketsaleapi.api.exception;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

import com.ada.ticketsaleapi.domain.exception.EntidadeNaoEncontradaException;
import com.ada.ticketsaleapi.domain.exception.VooEsgotadoException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public Mono<ResponseEntity<Object>> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex,
			ServerWebExchange exchange) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String type = HttpStatus.NOT_FOUND.name();
		String message = ex.getMessage();

		Problem body = createProblemBuilder(status.value(), type, message);

		return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, exchange);

	}

	@ExceptionHandler(VooEsgotadoException.class)
	public Mono<ResponseEntity<Object>> handleVooEsgotadoException(VooEsgotadoException ex,
			ServerWebExchange exchange) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String type = HttpStatus.BAD_REQUEST.name();
		String message = ex.getMessage();

		Problem body = createProblemBuilder(status.value(), type, message);

		return super.handleExceptionInternal(ex, body, new HttpHeaders(), status, exchange);

	}

	private Problem createProblemBuilder(Integer status, String type, String message) {
		return Problem.builder().status(status).type(type).message(message).timestamp(OffsetDateTime.now()).build();
	}

	@JsonInclude(Include.NON_NULL)
	@Getter
	@Builder
	public static class Problem {

		private Integer status;

		private OffsetDateTime timestamp;

		private String type;

		private String message;

	}

}
