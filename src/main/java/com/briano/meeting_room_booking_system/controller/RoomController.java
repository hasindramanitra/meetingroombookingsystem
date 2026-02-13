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

import com.briano.meeting_room_booking_system.dto.RoomDTO;
import com.briano.meeting_room_booking_system.service.RoomServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

	private final RoomServiceImpl roomServiceImpl;
	
	public RoomController(RoomServiceImpl roomServiceImpl) {
		this.roomServiceImpl = roomServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<RoomDTO>> getAllRooms() {
		List<RoomDTO> roomDTOs = this.roomServiceImpl.getAllRooms();
		
		return ResponseEntity.ok(roomDTOs);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
		RoomDTO roomDTO = this.roomServiceImpl.getRoomById(id);
		
		return ResponseEntity.ok(roomDTO);
	}
	
	@PostMapping
	public ResponseEntity<RoomDTO> createRoom( @Valid @RequestBody RoomDTO roomDTO) {
		RoomDTO createRoomDTO = this.roomServiceImpl.createRoom(roomDTO);
		
		URI location = URI.create("/api/v1/rooms" + createRoomDTO.getId());
		
		return ResponseEntity.created(location).body(createRoomDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDTO roomDTO) {
		RoomDTO updatedRoomDTO = this.roomServiceImpl.updateRoom(id, roomDTO);
		
		return ResponseEntity.ok(updatedRoomDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
		this.roomServiceImpl.deleteRoom(id);
		
		return ResponseEntity.noContent().build();
	}
}
