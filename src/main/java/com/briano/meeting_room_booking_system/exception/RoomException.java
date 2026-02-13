package com.briano.meeting_room_booking_system.exception;

public class RoomException extends RuntimeException{

	private String errorCode;
	
	public RoomException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public RoomException(String message, String errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
}
