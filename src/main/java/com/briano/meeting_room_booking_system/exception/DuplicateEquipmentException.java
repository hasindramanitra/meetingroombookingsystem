package com.briano.meeting_room_booking_system.exception;

public class DuplicateEquipmentException extends EquipmentException {

	public DuplicateEquipmentException(String equipmentName) {
		super("An equipment with the name "+ equipmentName + " already exists", "DUPLICATE_EQUIPMENT");
	}
}
