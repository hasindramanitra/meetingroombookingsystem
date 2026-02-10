package com.briano.meeting_room_booking_system.service;

import java.util.List;

import com.briano.meeting_room_booking_system.dto.EquipmentDTO;
import com.briano.meeting_room_booking_system.entity.Equipment;

public interface EquipmentService {

	EquipmentDTO createEquipment(EquipmentDTO equipmentDTO);
	
	EquipmentDTO getEquipmentById(Long id);
	
	List<EquipmentDTO> getAllEquipments();
	
	EquipmentDTO updateEquipment(Long id, EquipmentDTO equipmentDTO);
	
	void deleteEquipment(Long id);
}
