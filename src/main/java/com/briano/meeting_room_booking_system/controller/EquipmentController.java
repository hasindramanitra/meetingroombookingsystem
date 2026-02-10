package com.briano.meeting_room_booking_system.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briano.meeting_room_booking_system.dto.EquipmentDTO;
import com.briano.meeting_room_booking_system.service.EquipmentServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/api/v1/equipments")
public class EquipmentController {

	private final EquipmentServiceImpl equipmentServiceImpl;
	
	public EquipmentController(EquipmentServiceImpl equipmentServiceImpl) {
		this.equipmentServiceImpl = equipmentServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
		List<EquipmentDTO> equipmentDTOs = this.equipmentServiceImpl.getAllEquipments();
		
		return ResponseEntity.ok(equipmentDTOs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EquipmentDTO> getEquipment(@PathVariable Long id) {
		EquipmentDTO equipmentDTO = this.equipmentServiceImpl.getEquipmentById(id);
		
		return ResponseEntity.ok(equipmentDTO);
	}
	
	@PostMapping
	public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody @Valid EquipmentDTO equipmentDTO) {
		EquipmentDTO createdEquipment = this.equipmentServiceImpl.createEquipment(equipmentDTO);
		
		URI location = URI.create("/api/v1/equipments/" + createdEquipment.getId());
		
		return ResponseEntity.created(location).body(createdEquipment);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EquipmentDTO> updateEquipment(
			@PathVariable Long id, 
			@RequestBody @Valid EquipmentDTO equipmentDTO) {
		EquipmentDTO updatedEquipment = this.equipmentServiceImpl.updateEquipment(id, equipmentDTO);
		
		return ResponseEntity.ok(updatedEquipment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
		this.equipmentServiceImpl.deleteEquipment(id);
		
		return ResponseEntity.noContent().build();
	}
}
