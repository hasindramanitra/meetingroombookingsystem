package com.briano.meeting_room_booking_system.exception;

public class ReservationConflictException extends RuntimeException {

	public ReservationConflictException(String message) {
		super(message);
	}
}
