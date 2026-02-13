package com.briano.meeting_room_booking_system.exception;

public class RoomNotFoundException extends RoomException{

	public RoomNotFoundException(Long id) {
		super("Room not found with the ID - " + id, "ROOM_NOT_FOUND");
	}
}
