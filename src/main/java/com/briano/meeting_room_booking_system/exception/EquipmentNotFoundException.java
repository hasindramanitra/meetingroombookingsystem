package com.briano.meeting_room_booking_system.exception;

public class EquipmentNotFoundException extends EquipmentException{

	public EquipmentNotFoundException(Long id) {
		super("Equipment not found with the ID : " + id, "EQUIPMENT_NOT_FOUND");
	}
}
