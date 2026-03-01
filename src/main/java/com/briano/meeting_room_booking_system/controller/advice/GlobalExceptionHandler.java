package com.briano.meeting_room_booking_system.controller.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.briano.meeting_room_booking_system.exception.RoomNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<Object> handleRoomNotFoundException(RoomNotFoundException ex, WebRequest request) {

		Map<String, Object> body = createErrorBody(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getErrorCode(), request);

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
		Map<String, Object> body = createErrorBody(HttpStatus.INTERNAL_SERVER_ERROR, "An internal error has occured",
				"INTERNAL_ERROR", request);

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleException(final AccessDeniedException exception, WebRequest request) {
		Map<String, Object> body = createErrorBody(HttpStatus.FORBIDDEN, "You are not authorized to perform this operation",
				"FORBIDDEN", request);
		return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
	}

	private Map<String, Object> createErrorBody(HttpStatus status, String message, String errorCode,
			WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", message);
		body.put("path", request.getDescription(false).replace("uri=", ""));

		return body;
	};
}
