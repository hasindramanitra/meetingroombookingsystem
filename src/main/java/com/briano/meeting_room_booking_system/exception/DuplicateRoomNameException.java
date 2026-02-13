package com.briano.meeting_room_booking_system.exception;

public class DuplicateRoomNameException extends RoomException{

	public DuplicateRoomNameException(String roomName) {
		super("A room with the name "+ roomName + " already exists", "DUPLICATE_ROOM");
	}
}
