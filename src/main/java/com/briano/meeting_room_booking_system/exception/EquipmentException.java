package com.briano.meeting_room_booking_system.exception;

public class EquipmentException extends RuntimeException {
	
	private final String errorCode;
	
	public EquipmentException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public EquipmentException(String message, String errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

}
